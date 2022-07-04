package com.example.fadinglightsapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fadinglightsapp.repository.FirebaseRepository

class GameViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    fun updateTime(time: Double) {
        repository.updateTime(time)
    }
}
