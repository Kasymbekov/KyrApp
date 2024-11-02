package com.example.kyrapp.utils.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vicmikhailau.maskededittext.MaskedEditText

object AuthValidator {
    fun validateEmail(
        etEmail: TextInputEditText,
        inputEmail: TextInputLayout
    ): Boolean {
        return when {
            trimAny(etEmail).isEmpty() -> {
                inputEmail.error = "Обязательное поле"
                false
            }

            !isEmailValid(trimAny(etEmail)) -> {
                inputEmail.error = "Заполните корректно"
                false
            }

            else -> {
                inputEmail.error = null
                true
            }
        }
    }

    fun validateNumber(etNumber: MaskedEditText, etNumberL: TextInputLayout): Boolean {
        return when {
            trimAny(etNumber).isEmpty() -> {
                etNumberL.error = "Обязательное поле"
                false
            }

            trimAny(etNumber).length != 17 -> {
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
        val password = trimAny(etPassword)

        return when {
            password.isEmpty() -> {
                etPasswordL.error = "Обязательное поле"
                false
            }

            password.length !in 8..12 -> {
                etPasswordL.error = "Пароль должен состоять из 8-12 символов"
                false
            }

            !password.containsDigit() -> {
                etPasswordL.error = "Должна быть минимум 1 цифра"
                false
            }

            !password.containsLowerCase() -> {
                etPasswordL.error = "Должна быть минимум 1 маленькая буква"
                false
            }

            !password.containsUpperCase() -> {
                etPasswordL.error = "Должна быть минимум 1 большая буква"
                false
            }

            else -> {
                etPasswordL.error = null
                return true
            }
        }
    }

    fun validatePasswordConfirm(
        etPassword: TextInputEditText,
        etPasswordL: TextInputLayout,
        etPasswordConfirm: TextInputEditText
    ): Boolean {
        val password = trimAny(etPassword)
        val passwordConfirm = trimAny(etPasswordConfirm)
        if (password != passwordConfirm) {
            etPasswordL.error = "Пароли не совпадают"
            return false
        }

        etPasswordL.error = null
        return true
    }

    fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun String.containsDigit(): Boolean = any { it.isDigit() }
private fun String.containsLowerCase(): Boolean = any { it.isLowerCase() }
private fun String.containsUpperCase(): Boolean = any { it.isUpperCase() }
private fun trimAny(et: EditText): String = et.text.toString().trim()
