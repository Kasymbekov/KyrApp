package com.example.kyrapp.ui.auth

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.kyrapp.R
import com.example.kyrapp.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
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
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        auth = Firebase.auth
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            registerUser(
                binding.etEmail.text.toString().trim(),
                binding.etPass.text.toString().trim()
            )

            // get the filtered phone number
            Log.d("nurs:", binding.etNumber.unMaskedText.toString())
        }

        binding.tvQuestion.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.btnGoogle.setOnClickListener {
            Toast.makeText(requireContext(), "Функция в разработке...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty() && binding.etPassConfirm.text.toString()
                .isNotEmpty() && binding.etNumber.text.toString()
                .isNotEmpty() && binding.etPass.text.toString() == binding.etPassConfirm.text.toString()
        ) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("nurs", "createUserWithEmail:success")
                        Toast.makeText(
                            context,
                            "Authentication succeed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        findNavController().navigate(R.id.codeFragment)
                        val user = auth.currentUser
//                    updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("nurs", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
//                    updateUI(null)
                    }
                }
        }

    }
}