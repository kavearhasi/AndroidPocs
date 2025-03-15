package com.example.workmanageronetime.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanageronetime.domain.MyRepository
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject



class GetRateWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workParams:WorkerParameters,
    private val repo:MyRepository
):CoroutineWorker(context,workParams) {
    override suspend fun doWork(): Result {
        return try {

            val response = repo.getRate()

                val rateJson = Gson().toJson(response.body())
                val workData = workDataOf("bitcoin_rate" to rateJson)
              Log.d("Myonetime","Inside the get rate worker")
               Result.success(workData)


        }
        catch (e:Exception){
//            Log.e("Myonetime",e.message.toString())
            Result.failure()
        }
    }
}