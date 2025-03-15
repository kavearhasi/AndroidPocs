package com.example.logins.login.data


import com.example.logins.login.data.userinfo.LoginRequest
import com.example.logins.login.data.userinfo.User
import retrofit2.Response
import retrofit2.http.Body

//emilyspass
//emily.johnson@x.dummyjson.com

import retrofit2.http.POST
import retrofit2.http.Query


interface LoginApi {

@POST("auth/login")
suspend  fun checkUser(
    @Body loginRequest: LoginRequest
): Response<User>

}