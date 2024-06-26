package com.example.kyrapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.kyrapp.R
import com.example.kyrapp.databinding.FragmentResetPassBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ResetPassFragment : Fragment() {
    private lateinit var binding: FragmentResetPassBinding

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
        binding = FragmentResetPassBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReset.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                resetPassword(email)
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Operation failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun resetPassword(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireActivity(),
                        "Please check your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.etEmail.setText("")
                }
            }
    }

}