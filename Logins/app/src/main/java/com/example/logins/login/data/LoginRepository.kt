package com.example.logins.login.data

import android.util.Log
import com.example.logins.login.data.userinfo.LoginRequest
import com.example.logins.remote.NetworkResult
import com.example.logins.remote.handleApi
import okhttp3.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApi: LoginApi) {

    suspend fun getUser(loginRequest: LoginRequest): NetworkResult {
        Log.d("repo","reached repo")
        return handleApi<Response> { loginApi.checkUser(loginRequest) }
    }


}