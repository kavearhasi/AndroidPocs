package com.example.workmanageronetime.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlin.random.Random

class BuyWorker  @AssistedInject
constructor(
    @Assisted private val context: Context,
    @Assisted private val workParams: WorkerParameters,
    ): CoroutineWorker(context,workParams) {
     override suspend fun doWork(): Result {
         return try {
        delay(2000)
             val pick   = pickYesOrNo()
             val data = workDataOf("suggestion" to pick)
             Result.success(data)
         }
         catch (e:Exception){
             Log.e("Myonetime","Inside the exception ${e.message.toString()}")
             return Result.failure()
         }

     }
     private fun pickYesOrNo(): String {
         Log.wtf("buyWorker", "Worker is running...")
         return if (Random.nextBoolean()) "yes" else "no"
     }
 }