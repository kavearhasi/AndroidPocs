package com.example.workmanageronetime.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface BitcoinApi {
    @GET("v1/bitcoin")
   suspend fun getbitcoinPrice(@Header("X-Api-Key") apiKey : String = "+VDtIpg9N06DSO4ftv91ww==EVKLAlJmHTcAkruC"):Response<BitcoinRateDTO>
}