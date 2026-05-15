package com.example.internship

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internship.databinding.ActivityFrontPageBinding

class FrontPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFrontPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrontPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnter.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}
