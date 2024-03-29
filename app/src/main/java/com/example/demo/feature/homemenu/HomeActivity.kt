package com.example.demo.feature.homemenu

import android.os.Bundle
import android.os.Parcelable
import com.example.demo.R
import com.example.demo.app.BaseActivity
import com.example.demo.app.viewBinding
import com.example.demo.databinding.ActivityHomeMenuBinding
import com.example.demo.help.Router
import com.example.demo.help.Routes
import kotlinx.parcelize.Parcelize


class HomeActivity : BaseActivity(R.layout.activity_home_menu) {

    private val binding by viewBinding(ActivityHomeMenuBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val doituong = DoiTuong("chaha",32)
        Router.open(this, Routes.HomeApp(binding.container.id, data = doituong))
    }
}

@Parcelize
class DoiTuong(
    val value :String,
    val value2 :Int
) : Parcelable