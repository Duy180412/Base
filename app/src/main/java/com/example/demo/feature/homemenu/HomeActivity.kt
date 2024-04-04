package com.example.demo.feature.homemenu

import android.os.Bundle
import com.example.demo.R
import com.example.demo.app.BaseActivity
import com.example.demo.app.viewBinding
import com.example.demo.databinding.ActivityHomeMenuBinding
import com.example.demo.extension.cast
import com.example.demo.help.FragmentRouting
import com.example.demo.help.Router
import com.example.demo.help.Routes
import com.example.demo.help.Routing
import com.example.demo.help.argumentExt
import com.example.demo.help.getActivity
import com.example.demo.help.getFragment
import com.example.demo.help.setContainerId


class HomeActivity : BaseActivity(R.layout.activity_home_menu) {

    private val binding by viewBinding(ActivityHomeMenuBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        argumentExt()?.let {
            it.setContainerId(binding.container.id)
            Router.open(this, it)
        }

    }
}