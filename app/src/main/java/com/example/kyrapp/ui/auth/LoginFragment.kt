package com.example.kyrapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kyrapp.R
import com.example.kyrapp.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root
        auth = Firebase.auth
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvQuestion.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.btnNext.setOnClickListener {
            signIn(binding.etEmail.text.toString().trim(), binding.etPass.text.toString().trim())
            //Toast.makeText(requireContext(), "Main page", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signIn(email: String, pass: String) {
        if (email.isNotEmpty()&&pass.isNotEmpty()){
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("nurs", "signInWithEmail:success")
                        Toast.makeText(
                            requireContext(),
                            "Authentication succeed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val user = auth.currentUser
//                    updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("nurs", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            requireContext(),
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
//                    updateUI(null)
                    }
                }
        }

    }

}