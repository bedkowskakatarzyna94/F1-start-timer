package com.example.fadinglightsapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fadinglightsapp.repository.FirebaseRepository

class RankingViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val users = repository.getUsers()
}
