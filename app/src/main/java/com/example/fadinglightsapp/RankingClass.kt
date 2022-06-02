package com.example.fadinglightsapp

import retrofit2.Call
import retrofit2.http.GET


interface RankingService {
    @GET("/ranking")
    fun getRanking(): Call<List<User>>
}

data class User(val name: String, val points: Double?)
