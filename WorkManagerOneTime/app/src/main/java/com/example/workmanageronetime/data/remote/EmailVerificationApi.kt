package com.example.workmanageronetime.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface EmailVerificationApi {
    @GET("v1/validateemail")
    fun verifyEmail(@Header("X-Api-Key") apiKey : String = "+VDtIpg9N06DSO4ftv91ww==EVKLAlJmHTcAkruC",email:String): Response<EmailVerificationDTO>
}