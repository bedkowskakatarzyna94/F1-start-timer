package com.example.fadinglightsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.data.User
import com.example.fadinglightsapp.viewmodels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment() {
    private val REG_DEBUG = "REG_DEBUG"
    private val fbAuth = FirebaseAuth.getInstance()
    private val userVm: UserViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSignUpClick()
    }

    private fun setupSignUpClick() {
        signUpButtonRegistration.setOnClickListener {
            val email = email_registration.text?.trim().toString()
            val pass = pass_registration.text?.trim().toString()
            val repeatPass = repeat_pass_registration.text?.trim().toString()
            val nick = nickname.text?.trim().toString()
            if (pass == repeatPass) {
                fbAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnSuccessListener { authRes ->
                        userVm.saveUser(User(email, nick, null, authRes.user?.uid))
                        if (authRes.user != null) startApp()
                    }
                    .addOnFailureListener { exc ->
                        Snackbar.make(
                            requireView(),
                            "Ups...Something went wrong...",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.d(REG_DEBUG, exc.message.toString())
                    }
            }
        }
    }
}



