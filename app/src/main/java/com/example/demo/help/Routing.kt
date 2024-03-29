package com.example.demo.help

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlinx.parcelize.Parcelize
import kotlin.reflect.KClass

interface Routing : Arguments

interface ActivityRouting : Routing {
    val clazzActivity: KClass<out Activity>
}

interface FragmentRouting : Routing {
    val clazzFragment: KClass<out Fragment>
    val clazzActivity: KClass<out Activity>
    val containerFragmentId: Int
    val typeTransaction: TypeTransaction
}

enum class TypeTransaction {
    ADD,
    REPLACE,
    REMOVE,
}