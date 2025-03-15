package com.example.quotes.data.repository

import com.example.quotes.data.local.QuotesDao
import com.example.quotes.data.remote.ApiService
import com.example.quotes.domain.models.Quotes
import com.example.quotes.domain.repository.QuotesRepo


class QuotesRepoImple(

    private val quoteDao:QuotesDao,
    private val api : ApiService
):QuotesRepo {
    override fun getQuote() {

    }
        suspend fun delete() = quoteDao.deleteAllQuotes()
    override suspend fun getAllQuotes(): List<Quotes>  = quoteDao.getAllQuotes()
    override fun setPeriodicWorkRequest() {
        TODO("Not yet implemented")
    }

}