package com.example.demo.help

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.example.demo.extension.cast
import kotlinx.parcelize.Parcelize


@Parcelize
class DataArguments(val data : Parcelable) : Parcelable
interface Arguments : Parcelable,  {

    @Suppress("DEPRECATION")
    companion object {
        val KEY: String = DataArguments::class.java.name
        fun getArgumentsFrom(extras: Bundle?): DataArguments? {
            extras ?: return null
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
               val value =  extras.getParcelable(KEY, DataArguments::class.java)
                value
            } else {
                val value = extras.getParcelable<Parcelable>(KEY).cast<DataArguments>()
                value
            }
        }
    }

    fun toBundle(): Bundle {
        return Bundle().also { it.putParcelable(KEY, ) }
    }
}

inline fun <reified T : Arguments> Fragment.lazyArgument() = lazy(LazyThreadSafetyMode.NONE) {
    println("chaythu3")
    val value = this.arguments
    println("chaythu2" + value.toString())
    Arguments.getArgumentsFrom(this.arguments).cast<T>()

}
