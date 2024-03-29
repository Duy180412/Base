package com.example.demo.feature.homemenu

import android.content.Context
import android.content.pm.LauncherApps
import android.os.Bundle
import android.os.UserManager
import android.view.View
import com.example.demo.R
import com.example.demo.app.BaseFragment
import com.example.demo.app.viewBinding
import com.example.demo.databinding.FragmentHomeAppBinding
import com.example.demo.help.Routes
import com.example.demo.help.lazyArgument
import com.example.demo.model.ItemHomeMenu
import com.example.demo.wiget.apdater.HomeMenuAdapter

class HomeAppFragment : BaseFragment(R.layout.fragment_home_app) {
    private val binding by viewBinding(FragmentHomeAppBinding::bind)
    private lateinit var adapterHomeMenu: HomeMenuAdapter
    private val args = lazyArgument<Routes.HomeApp>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("chaythu" + arguments.toString())
//        adapterHomeMenu = HomeMenuAdapter(binding.menuHome)
//        initListApp()
    }

    private fun initListApp() {
        val userManager = self.getSystemService(Context.USER_SERVICE) as UserManager
        val launcherApps = self.getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps
        val listApp = ArrayList<ItemHomeMenu>()
        userManager.userProfiles.map {
            launcherApps.getActivityList(null, it).map { activityInfo ->
                listApp.add(
                    ItemHomeMenu(
                        activityInfo.applicationInfo.loadLabel(self.packageManager).toString(),
                        activityInfo.applicationInfo.loadIcon(self.packageManager)
                    )
                )
            }
        }
        adapterHomeMenu.setListApp(listApp)
    }

}