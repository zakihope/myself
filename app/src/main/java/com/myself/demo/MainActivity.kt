package com.myself.demo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptions.Builder
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.gson.Gson
import com.myself.demo.databinding.ActivityMainBinding
import com.myself.demo.model.User

class MainActivity : AppCompatActivity() {

    private var MY_REQUEST_CODE = 100
    private lateinit var appUpdateManager: AppUpdateManager
    private val installStateUpdatedListener =
        InstallStateUpdatedListener { state ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                // Trigger the completion of the update
                appUpdateManager.completeUpdate()
            }
        }
    private lateinit var binding: ActivityMainBinding // Generated binding class
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Full Screen Layout, StatusBar Dark Text, NavigationBar Dark Icon
        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        }

        //update google play
        appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateManager.registerListener(installStateUpdatedListener)

        //navigatio view
        navController = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        // Set the initial destination using NavOptions
        val initialDestination = if (loadSessionData() == false) {
            R.id.helloFragment
        } else {
            R.id.homeFragment
        }
        val navOptions = Builder()
            .setPopUpTo(initialDestination, true)
            .build()
        navController.navigate(initialDestination, null, navOptions)

        //navigation bar item
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    run {
                        val options: NavOptions = Builder()
                            .setLaunchSingleTop(true)
                            .setPopUpTo(R.id.homeFragment, true)
                            .build()
                        navController.navigate(R.id.homeFragment, null, options)
                    }
                    true
                }

                R.id.categourieFragment -> {
                    navController.navigate(R.id.categourieFragment)
                    true
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                // Request the update
                startImmediateUpdate(appUpdateInfo)
            }
        }
    }

    private fun startImmediateUpdate(appUpdateInfo: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            AppUpdateType.IMMEDIATE,
            this,
            MY_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                // If the update is canceled or fails, you can decide how to handle it
            }
        }
    }

    fun loadSessionData(): Boolean? {
        val sharedPreferences = getSharedPreferences("cach", Context.MODE_PRIVATE)
        val session = sharedPreferences?.getBoolean("session", false)
        return session
    }
    fun loadUserData(): User? {
        val sharedPreferences = getSharedPreferences("cach", Context.MODE_PRIVATE)
        val json = sharedPreferences?.getString("user", null)
        val gson = Gson()
        return gson.fromJson(json, User::class.java)
    }
    fun loadTestData(): Int? {
        val sharedPreferences = getSharedPreferences("cach", Context.MODE_PRIVATE)
        return sharedPreferences?.getInt("QuizDone", 0)
    }
    fun loadTestResult(): Int? {
        val sharedPreferences = getSharedPreferences("cach", Context.MODE_PRIVATE)
        return sharedPreferences?.getInt("testResult", 0)
    }

    fun clearUserData() {
        val user = getSharedPreferences("cach", MODE_PRIVATE)
        val editor = user.edit()
        editor.remove("user")
        editor.clear()
        editor.apply()
    }
}