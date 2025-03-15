package com.example.workmanageronetime.data.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.workmanageronetime.data.worker.BuyWorker
import com.example.workmanageronetime.data.worker.GetRateWorker
import com.example.workmanageronetime.domain.MyRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Myapp : Application(), Configuration.Provider {
    @Inject
    lateinit var customWorkerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(customWorkerFactory)
            .build()

    companion object {
        const val CHANNEL_ID = "rate_channel"
    }

    override fun onCreate() {
        super.onCreate()
      createNotification()
    }

    private fun createNotification() {
        val name = "BitcoinRate Notification"
        val descriptionText = "This channel is for sending notification about the rate"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

class CustomWorkerFactory @Inject constructor(
    private val repo: MyRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            GetRateWorker::class.java.name -> GetRateWorker(appContext, workerParameters, repo)
            BuyWorker::class.java.name -> BuyWorker(appContext, workerParameters)
            else -> null
        }
    }
}

