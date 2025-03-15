package com.example.workmanageronetime.data.worker

import android.app.NotificationManager
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanageronetime.domain.MyRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class EmojiWorker @AssistedInject constructor(
    @Assisted private val context:Context,
    @Assisted private val workParams:WorkerParameters,
    private val repo:MyRepository
):CoroutineWorker(context,workParams) {
   val notifcationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    override suspend fun doWork(): Result {
        return Result.success()
    }

}