package com.example.logins.login.data.userinfo

class LoginRequest (
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)