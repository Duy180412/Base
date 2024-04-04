package com.example.demo.feature.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.demo.R
import com.example.demo.app.BaseActivity
import com.example.demo.help.Router
import com.example.demo.help.Routes

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Router.open(this, Routes.HomeApp())
    }
}