package com.example.demo.app

import androidx.fragment.app.Fragment

abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {
    val self get() =  requireActivity()
}