package com.example.internship.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.internship.databinding.FragmentAddHomeStayBinding
import com.example.internship.model.HomeStay
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddHomeStayFragment : Fragment() {

    private var _binding: FragmentAddHomeStayBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHomeStayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.btnSave.setOnClickListener {
            saveHomeStay()
        }
    }

    private fun saveHomeStay() {
        val name = binding.etName.text.toString().trim()
        val desc = binding.etDescription.text.toString().trim()
        val distance = binding.etDistance.text.toString().trim()
        val price = binding.etPrice.text.toString().trim()
        val imageUrl = binding.etImageUrl.text.toString().trim()

        if (name.isEmpty() || desc.isEmpty() || distance.isEmpty() || price.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.isEnabled = false

        val homeStayId = db.collection("homestays").document().id
        val homeStay = HomeStay(
            id = homeStayId,
            placeName = name,
            description = desc,
            distance = distance,
            price = price,
            imageUrl = imageUrl,
            ownerId = auth.currentUser?.uid ?: ""
        )

        db.collection("homestays").document(homeStayId)
            .set(homeStay)
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Home Stay Added Successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            .addOnFailureListener { e ->
                binding.progressBar.visibility = View.GONE
                binding.btnSave.isEnabled = true
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
