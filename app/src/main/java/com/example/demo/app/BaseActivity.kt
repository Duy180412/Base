package com.example.demo.app

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    val self get() = this
}