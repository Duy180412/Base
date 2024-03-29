package com.example.demo.help

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.example.demo.extension.cast


interface DataArguments {
    val data: Parcelable?
}

interface Arguments : Parcelable, DataArguments {

    @Suppress("DEPRECATION")
    companion object {
        val KEY: String = DataArguments::class.java.name
        inline fun <reified T : Parcelable> getArgumentsFrom(extras: Bundle?): T? {
            extras ?: return null
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras.getParcelable(KEY, T::class.java)
            } else {
                extras.getParcelable<Parcelable>(KEY).cast<T>()
            }
        }
    }

    fun toBundle(): Bundle {
        return Bundle().also { it.putParcelable(KEY, this.data) }
    }
}

inline fun <reified T : Parcelable> Fragment.lazyArgument(): T? {
    return Arguments.getArgumentsFrom<T>(this.arguments)
}
