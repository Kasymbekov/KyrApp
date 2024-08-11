//package com.example.kyrapp.ui.auth
//
//import android.content.Context
//import android.content.IntentSender
//import com.example.kyrapp.R
//import com.google.android.gms.auth.api.identity.BeginSignInRequest
//import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
//import com.google.android.gms.auth.api.identity.BeginSignInResult
//import com.google.android.gms.auth.api.identity.SignInClient
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//
//class GoogleAuthUiClient(
//    private val context: Context,
//    private val oneTapClient: SignInClient
//) {
//    private val auth = Firebase.auth
//
//    suspend fun signIn(): IntentSender? {
//        val result = try {
//            oneTapClient.beginSignIn(
//                buildSignInRequest()
//            ).addOnSuccessListener {  }
//        } catch (e: Exception) {
//
//        }
//    }
//
//    private fun buildSignInRequest(): BeginSignInRequest {
//        return BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(context.getString(R.string.web_client_id))
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(false)
//                    .build()
//            )
//            .setAutoSelectEnabled(true)
//            .build()
//    }
//
//}