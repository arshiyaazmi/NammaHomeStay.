package com.example.internship.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internship.R
import com.example.internship.databinding.FragmentHomeBinding
import com.example.internship.model.HomeStay
import com.example.internship.ui.adapter.HomeStayAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: HomeStayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()

        setupRecyclerView()
        fetchHomeStays()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addHomeStayFragment)
        }

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_profile) {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                true
            } else {
                false
            }
        }
        
        // Add profile menu item programmatically or via XML
        binding.toolbar.inflateMenu(R.menu.home_menu)
    }

    private fun setupRecyclerView() {
        adapter = HomeStayAdapter(emptyList())
        binding.rvHomestays.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHomestays.adapter = adapter
    }

    private fun fetchHomeStays() {
        binding.progressBar.visibility = View.VISIBLE
        db.collection("homestays")
            .get()
            .addOnSuccessListener { result ->
                binding.progressBar.visibility = View.GONE
                val list = result.toObjects(HomeStay::class.java)
                adapter.updateList(list)
            }
            .addOnFailureListener { e ->
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Error fetching data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
