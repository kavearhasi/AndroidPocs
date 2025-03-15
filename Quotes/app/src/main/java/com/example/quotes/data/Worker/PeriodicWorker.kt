package com.example.quotes.data.Worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quotes.data.local.QuotesDao

import com.example.quotes.data.remote.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


const val PERIODIC_WORK_REQUEST = "PERIODIC_WORK_REQUEST"

//@HiltWorker
//class PeriodicWorker @AssistedInject constructor(
//    @Assisted context: Context,
//    @Assisted workParams: WorkerParameters,
//    private val api: ApiService,
//    private val quotesDao: QuotesDao
//
//
//) : CoroutineWorker(context, workParams) {
//    override suspend fun doWork(): Result {
////        return try {
////            val response = api.getQuote()
////            quotesDao.insert(response)
////            Result.success()
////        } catch (e: Exception) {
////            Result.failure()
////        }
//    }
//}