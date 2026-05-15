package com.example.internship.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.internship.R
import com.example.internship.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            signupUser()
        }

        binding.tvGoToLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun signupUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnSignup.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.progressBar.visibility = View.GONE
                binding.btnSignup.isEnabled = true
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Signup Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Signup Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
