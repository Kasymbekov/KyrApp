package com.example.kyrapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.vicmikhailau.maskededittext.MaskedEditText
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val RC_SIGN_IN = 9001
    }

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
//            registerUser(
//                binding.etEmail.text.toString().trim(),
//                binding.etPass.text.toString().trim()
//            )

            // get the filtered phone number
            Log.d("nurs:", binding.etNumber.unMaskedText.toString())
        }

        binding.tvQuestion.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validateEmail(binding.etEmail, binding.inputEmail)
            }
        })


        // number validation
        binding.etNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validateNumber(binding.etNumber, binding.inputNumber)
            }
        })

        // password validation
        binding.etPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validatePassword(binding.etPass, binding.inputPass)
            }

        })

        // password confirmation validation
        binding.etPassConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validatePasswordConfirm(
                    binding.etPassConfirm,
                    binding.inputPassConfirm,
                    binding.etPass
                )
            }
        })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.btnGoogle.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
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
                        //  updateUI(user)
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

    private fun validateEmail(
        etEmail: TextInputEditText,
        inputEmail: TextInputLayout
    ): Boolean {
        return when {
            etEmail.text.toString().trim().isEmpty() -> {
                inputEmail.error = "Обязательное поле"
                false
            }

            !isEmailValid(etEmail.text.toString().trim()) -> {
                inputEmail.error = "Заполните корректно"
                false
            }

            else -> {
                inputEmail.error = null
                true
            }
        }
    }

    private fun validateNumber(etNumber: MaskedEditText, etNumberL: TextInputLayout): Boolean{
        return when {
            etNumber.text.toString().trim().isEmpty() -> {
                etNumberL.error = "Обязательное поле"
                false
            }

            etNumber.text.toString().trim().length != 17 -> {
                etNumberL.error = "Заполните корректно"
                false
            }

            else -> {
                etNumberL.error = null
                true
            }
        }
    }

    private fun validatePassword(
        etPassword: TextInputEditText,
        etPasswordL: TextInputLayout
    ): Boolean {
        val oneLowerCase = Regex("[a-z]+")
        val oneUpperCase = Regex("[A-Z]+")
        val oneNumeric = Regex("\\d") // matches any digit

        return when {
            etPassword.text.toString().trim().isEmpty() -> {
                etPasswordL.error = "Обязательное поле"
                false
            }

            etPassword.text.toString().trim().length < 8 || etPassword.text.toString()
                .trim().length > 12 -> {
                etPasswordL.error = "Пароль должен состоять из 8-12 символов"
                false
            }

            !oneNumeric.containsMatchIn(etPassword.text.toString().trim()) -> {
                etPasswordL.error = "Должна быть минимум 1 цифра"
                false
            }

            !oneLowerCase.containsMatchIn(etPassword.text.toString().trim()) -> {
                etPasswordL.error = "Должна быть минимум 1 маленькая буква"
                false
            }

            !oneUpperCase.containsMatchIn(etPassword.text.toString().trim()) -> {
                etPasswordL.error = "Должна быть минимум 1 большая буква"
                false
            }

            else -> {
                etPasswordL.error = null
                true
            }
        }
    }

    private fun validatePasswordConfirm(
        etPassword: TextInputEditText,
        etPasswordL: TextInputLayout,
        etPasswordConfirm: TextInputEditText
    ): Boolean {

        return when {
            etPassword.text.toString().trim().isEmpty() -> {
                etPasswordL.error = "Обязательное поле"
                false
            }

            etPassword.text.toString().trim().length < 8 || etPassword.text.toString()
                .trim().length > 12 -> {
                etPasswordL.error = "Пароль должен состоять из 8-12 символов"
                false
            }

            etPassword.text.toString().trim() != etPasswordConfirm.text.toString().trim() -> {
                etPasswordL.error = "Пароли не совпадают"
                false
            }

            else -> {
                etPasswordL.error = null
                true
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, /*accessToken=*/ null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("nurs", "Authentication succeed.")
                    Toast.makeText(
                        requireContext(),
                        "Successfully signed as ${auth.currentUser?.displayName.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.mainScreenActivity)
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("nurs", "Authentication failed.")
                    //updateUI(user)
                }

            }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}