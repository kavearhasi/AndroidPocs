package com.example.quotes.data.remote

import com.example.quotes.data.model.QuoteDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiService {
    @GET("/v1/quotes")
    suspend fun getQuote(@Header("X-Api-Key") apiKey : String = "OvJpnVKtSfcPGRC2TsDRag==XcieGmXMOlazu9cM") : Response<List<QuoteDto>>
}