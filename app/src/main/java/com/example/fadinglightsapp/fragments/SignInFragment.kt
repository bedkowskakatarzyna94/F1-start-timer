package com.example.fadinglightsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fadinglightsapp.R
//import com.example.fadinglightsapp.SignInFragmentDirections
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment() {

    private val fbAuth = FirebaseAuth.getInstance()
    private val LOG_DEBUG = "LOG_DEBUG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginClick()
        setupRegistrationClick()
    }

    private fun setupRegistrationClick() {
        signUpButton.setOnClickListener{
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment().actionId)
        }
    }

    private fun setupLoginClick() {
        loginButton.setOnClickListener {
            val email = emailLoginInput.text?.trim().toString()
            val pass = passLoginInput.text?.trim().toString()

            fbAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener { authRes ->
                    if (authRes.user != null) startApp()
                }
                .addOnFailureListener { exc ->
                    Snackbar.make(
                        requireView(),
                        "Ups...Something went wrong...",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.d(LOG_DEBUG, exc.message.toString())
                }

        }
    }
}

