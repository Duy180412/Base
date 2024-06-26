package com.example.demo.help

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultCaller
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.demo.extension.cast
import com.example.demo.feature.homemenu.HomeActivity
import kotlin.reflect.KClass

object Router {

    fun open(ower: ActivityResultCaller, routing: Routing) {
        startActivity(ower, routing)
    }

    private fun startActivity(ower: ActivityResultCaller, routing: Routing) {
        when (ower) {
            is Activity -> {
                when (routing) {
                    is ActivityRouting -> ower.startActivity(createIntent(ower, routing))
                    is FragmentRouting -> {
                        if (ower::class == routing.clazzActivity) {
                           transitionFragment(ower,routing)
                        } else ower.startActivity(createIntent(ower, routing))

                    }
                }
            }

            is Fragment -> {
                ower.startActivity(createIntent(ower.requireContext(), routing))
            }
        }
    }

    private fun transitionFragment(ower: Activity, routing: FragmentRouting) {
        val supportManager = ower.cast<FragmentActivity>()?.supportFragmentManager
            ?: error("Can't not find  supportFragmentManager in $routing")
        val transition = supportManager.beginTransaction()
        when (routing.typeTransaction) {
            TypeTransaction.ADD ->
                transition.add(
                    routing.containerFragmentId,
                    routing.clazzFragment.java,
                    routing.toBundle(),
                    createTag(routing.clazzFragment)
                ).commit()

            TypeTransaction.REPLACE -> {
                transition.replace(
                    routing.containerFragmentId,
                    routing.clazzFragment.java,
                    routing.toBundle(),
                    createTag(routing.clazzFragment)
                ).commit()
            }

            TypeTransaction.REMOVE -> {
                val fragment =
                    supportManager.findFragmentByTag(routing.clazzFragment::class.java.simpleName)
                if (fragment != null) transition.remove(fragment).commit()
            }
        }
    }

    private fun createIntent(context: Context, routing: Routing): Intent {
        val intent = when (routing) {
            is ActivityRouting -> Intent(context, routing.clazzActivity.java).apply {
                putExtras(
                    routing.toBundle()
                )
            }

            is FragmentRouting -> Intent(context, routing.clazzActivity.java).apply {
                putExtras(routing.toBundle())
            }

            else -> error("Routing invalid")
        }
        return intent
    }

    private fun createTag(clazz: KClass<out Fragment>): String {
        return clazz.java.simpleName
    }
}