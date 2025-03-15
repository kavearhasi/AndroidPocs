package com.example.quotes.domain.repository

import com.example.quotes.domain.models.Quotes
import kotlinx.coroutines.flow.Flow

interface QuotesRepo {

    fun getQuote()

    suspend fun getAllQuotes(): List<Quotes>

    fun setPeriodicWorkRequest()
}