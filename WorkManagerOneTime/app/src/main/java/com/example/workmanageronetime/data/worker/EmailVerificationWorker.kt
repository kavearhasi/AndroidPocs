package com.example.workmanageronetime.data.worker;


import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanageronetime.domain.MyRepository

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;



class EmailVerificationWorker @AssistedInject
constructor(
    @Assisted private val context: Context,
    @Assisted private val workParams: WorkerParameters,
    private val repo: MyRepository,

): CoroutineWorker(context,workParams) {
    override suspend fun doWork(): Result {
        return try {
             val email:String = inputData.getString("email").toString()
            val response = repo.isValidEmail(email)
            if (response.isSuccessful){
                val isValid  = response.body()?.is_valid
                val workData = workDataOf("valid_email" to isValid)
                Result.success(workData)


            }
            Result.retry()
        }
        catch (e:Exception){
            Log.e("Email verification",e.message.toString())
            Result.failure()
        }
    }
}
