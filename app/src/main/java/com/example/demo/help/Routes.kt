package com.example.demo.help

import android.app.Activity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.example.demo.feature.homemenu.HomeActivity
import com.example.demo.feature.homemenu.HomeAppFragment
import kotlinx.parcelize.Parcelize
import kotlin.reflect.KClass

object Routes {
    @Parcelize
    class HomeAct(
        override val data: Parcelable? = null
    ) : ActivityRouting {
        override val clazzActivity: KClass<out Activity>
            get() = HomeActivity::class

    }

    @Parcelize
    class HomeApp(
        override var containerFragmentId: Int = ContainerFragmentIdDefault,
        override val data: Parcelable? = null,
        override val typeTransaction: TypeTransaction = TypeTransaction.ADD,
    ) : FragmentRouting {
        override val clazzFragment: KClass<out Fragment>
            get() = HomeAppFragment::class
        override val clazzActivity: KClass<out Activity>
            get() = HomeActivity::class
    }
}