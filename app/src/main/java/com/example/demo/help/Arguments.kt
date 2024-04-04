package com.example.demo.help

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.demo.extension.cast
import kotlin.reflect.KClass


interface DataArguments {
    val data: Parcelable?
}

interface Arguments : Parcelable, DataArguments {

    @Suppress("DEPRECATION")
    companion object {
        val KEY: String = Arguments::class.java.name
        fun getArgumentsFrom(extras: Bundle?): Routing? {
            extras ?: return null
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras.getParcelable(KEY, Routing::class.java)
            } else {
                extras.getParcelable<Parcelable>(KEY).cast<Routing>()
            }
        }
    }

    fun toBundle(): Bundle {
        return Bundle().also { it.putParcelable(KEY, this) }
    }
}

fun Fragment.argumentExt(): Routing? {
    return Arguments.getArgumentsFrom(this.arguments)
}

fun FragmentActivity.argumentExt(): Routing? {
    return Arguments.getArgumentsFrom(intent.extras)
}

fun Routing.getActivity(): KClass<out Activity>? {
    return this.cast<ActivityRouting>()?.clazzActivity
}

fun Routing.getFragment(): KClass<out Fragment>? {
    return this.cast<FragmentRouting>()?.clazzFragment
}

fun Routing.setContainerId(containerId: Int) {
    this.cast<FragmentRouting>()?.containerFragmentId = containerId
}

fun Routing.getContainerId(): Int {
    return this.cast<FragmentRouting>()?.containerFragmentId ?: ContainerFragmentIdDefault
}

fun Routing.getTypeTransaction(): TypeTransaction {
    return this.cast<FragmentRouting>()?.typeTransaction ?: TypeTransaction.ADD
}

inline fun <reified T : Parcelable> Routing.getData(): T? {
    return this.data.cast<T>()
}
