package com.example.fadinglightsapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fadinglightsapp.data.User
import com.example.fadinglightsapp.repository.FirebaseRepository

class UserViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun saveUser(user: User) {
        repository.saveUser(user)
    }
}
