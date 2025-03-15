package com.example.logins.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.logins.databinding.ActivityLoginBinding
import com.example.logins.home.ui.HomeActivity
import com.example.logins.login.data.userinfo.LoginRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val model: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userName: String
        var password: String

        binding.loginBtn.setOnClickListener {
            userName = binding.getUserEmail.text.toString()
            password = binding.getUserPassword.text.toString()
            initListeners(userName, password)
            initObserves(userName, password)
        }

    }

    private fun initListeners(userName: String, password: String) {

        binding.loadingBar.isVisible = true

        if (!checkInput(userName, password)) {
            Toast.makeText(
                this,
                "Please enter email && password .Both fields are required",
                Toast.LENGTH_SHORT
            ).show()
            binding.loadingBar.isVisible = false
            return
        }

        model.checkUserAuth(LoginRequest(userName, password))
    }

    private fun initObserves(userName: String, password: String) {

        model.authResponse.observe(this) { res ->
            binding.loadingBar.isVisible = false
            if (res == "_success") {
                lifecycleScope.launch {
                    model.storeLogins(userName, password)
                }
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else if (res == "_exception" || res == "_unknown") {
                Toast.makeText(
                    this,
                    "Sorry! unknown Error Please try again Later",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e("LoginActivity", res)
                if (res == "Bad Request" || res == "Not Found") {
                    Toast.makeText(this, "wrong User name or password ", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Sorry! Server error ", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun checkInput(userName: String, password: String): Boolean {
        return !(userName.isEmpty() || password.isEmpty())
    }
}