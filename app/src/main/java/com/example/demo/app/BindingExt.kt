package com.example.demo.app

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding


fun <T : ViewBinding> AppCompatActivity.viewBinding(function: View.() -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        val contentView = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
        function(contentView)
    }

fun <T : ViewBinding> Fragment.viewBinding(function: View.() -> T): Lazy<T> = object :
    Lazy<T>,
    LifecycleEventObserver {
    private var mValue: T? = null

    override val value: T
        get() {
            if (mValue == null) mValue = function(
                view ?: error(
                    "Fragment view is not created yet!, " +
                            "please ensure binding called at onViewCreated"
                )
            )
            return mValue!!
        }

    init {
        viewLifecycleOwnerLiveData.observe(this@viewBinding) {
            it?.lifecycle?.addObserver(this)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            source.lifecycle.removeObserver(this)
            mValue = null
        }
    }

    override fun isInitialized(): Boolean {
        return mValue != null
    }
}
