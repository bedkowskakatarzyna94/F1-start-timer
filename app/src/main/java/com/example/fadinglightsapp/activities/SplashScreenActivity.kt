//package com.example.fadinglightsapp
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//
//class SplashScreenActivity : Activity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash_screen)
//        Handler(Looper.myLooper()!!).postDelayed({ startMainActivity() }, 2000)
//    }
//
//    private fun startMainActivity() {
//        startActivity(Intent(applicationContext, MainActivity::class.java))
//    }
//}