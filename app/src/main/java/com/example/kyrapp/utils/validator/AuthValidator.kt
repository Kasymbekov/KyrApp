package com.example.kyrapp.utils.validator

import android.content.Context
import com.example.kyrapp.ui.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vicmikhailau.maskededittext.MaskedEditText

object AuthValidator {

    fun validateEmail(
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

    fun validateNumber(etNumber: MaskedEditText, etNumberL: TextInputLayout): Boolean{
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

    fun validatePassword(
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

    fun validatePasswordConfirm(
        etPassword: TextInputEditText,
        etPasswordL: TextInputLayout,
        etPasswordConfirm: TextInputEditText
    ): Boolean {

        return when {
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

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}