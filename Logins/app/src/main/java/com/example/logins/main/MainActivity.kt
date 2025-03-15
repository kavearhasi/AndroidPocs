package com.example.logins.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.logins.databinding.ActivityMainBinding
import com.example.logins.home.ui.HomeActivity
import com.example.logins.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var isLoading = true
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition {
          true
        }
        enableEdgeToEdge()
        lifecycleScope.launch {
            model.getUserDetails()
            try {
                val isUserStatus = model.getUserDetails()

                Log.e("MainActivityss", isUserStatus.toString())

                splash.setKeepOnScreenCondition{false}
                if (isUserStatus ) {
                    Log.e("MainActivity","Inside Home")
                    navToHomeActivity()
                } else {
                    Log.e("MainActivity","Inside Login")
                    navToLoginActivity()
                }
            } catch (e: Exception) {
                Log.e("MainActivityss", "Error checking login status: ${e.message}")
                splash.setKeepOnScreenCondition{false}

                navToLoginActivity()
            }
        }
        actionBar?.hide()
    }

    private fun navToHomeActivity() {
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navToLoginActivity() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}