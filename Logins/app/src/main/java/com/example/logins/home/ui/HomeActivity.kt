package com.example.logins.home.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.logins.databinding.ActivityHomeBinding
import com.example.logins.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val model: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOut.setOnClickListener {
            model.logOut()
            navToLogin()
            finish()
        }



    }
    private fun navToLogin(){
        val intent = Intent(this@HomeActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}