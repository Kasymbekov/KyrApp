package com.example.kyrapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.kyrapp.R
import com.example.kyrapp.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.util.regex.Pattern
import kotlin.concurrent.thread

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backPressedCallback = object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, backPressedCallback)
    }

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
            var email = binding.etEmail.text.toString().trim()
            var pass = binding.etPass.text.toString().trim()
//            if (isEmailValid(email)){
//                Toast.makeText(requireContext(), "Email is valid", Toast.LENGTH_SHORT).show()
//            } else{
//                Toast.makeText(requireContext(), "Email is invalid", Toast.LENGTH_SHORT).show()
//            }
            signIn(email, pass)
            // throw RuntimeException("Test Crash")
        }

        binding.tvResetPass.setOnClickListener {
            findNavController().navigate(R.id.resetPassFragment)
        }
    }

    private fun signIn(email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
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
                        findNavController().navigate(R.id.action_loginFragment_to_mainScreenActivity)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("nurs", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            requireContext(),
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }

    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}