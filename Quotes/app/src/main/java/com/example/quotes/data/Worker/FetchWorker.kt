package com.example.quotes.data.Worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quotes.data.local.QuotesDao
import com.example.quotes.data.remote.ApiService
import com.example.quotes.domain.models.Quotes
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject



@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workParams: WorkerParameters,
    private val api: ApiService,
    private val quotesDao: QuotesDao


) : CoroutineWorker(context, workParams) {
    override suspend fun doWork(): Result {
        return try {
            val response = api.getQuote()
            if(response.isSuccessful) {
                val body = response.body()!!
                body.forEach {
                    Log.e("responses","${0},${it.author},${it.category},${it.quote}}")
                     quotesDao.insert(Quotes(0,it.author,it.category,it.quote))
                    val storedQuotes = quotesDao.getAllQuotes() // Ensure this is a suspend function
                    Log.e("FetchWorker", "Inserted Quotes: $storedQuotes")
                }
                Result.success()
            }
            Result.success()
        } catch (e: Exception) {

            e.message?.let { Log.e("Fetch worker"," From the worker exception $it") }
            Result.failure()
        }
    }
}