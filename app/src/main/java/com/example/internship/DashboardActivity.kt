package com.example.internship

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internship.databinding.ActivityDashboardBinding
import com.example.internship.model.HomeStay
import com.google.firebase.firestore.FirebaseFirestore

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: HomestayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        setupRecyclerView()
        fetchHomeStays()

        binding.cardViewHomestays.setOnClickListener {
            fetchHomeStays()
            Toast.makeText(this, "Refreshing stays...", Toast.LENGTH_SHORT).show()
        }

        binding.cardAddHomestay.setOnClickListener {
            // Since AddHomeStay is currently a Fragment in MainActivity, 
            // we start MainActivity which handles the navigation.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.cardProfile.setOnClickListener {
            // Similarly for Profile
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = HomestayAdapter(emptyList())
        binding.rvDashboard.layoutManager = LinearLayoutManager(this)
        binding.rvDashboard.adapter = adapter
    }

    private fun fetchHomeStays() {
        db.collection("homestays")
            .get()
            .addOnSuccessListener { result ->
                val list = result.toObjects(HomeStay::class.java)
                adapter.updateData(list)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
