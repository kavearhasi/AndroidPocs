package com.example.workmanageronetime.presentation




import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.workmanageronetime.data.remote.BitcoinRateDTO
import com.example.workmanageronetime.data.worker.BuyWorker
import com.example.workmanageronetime.data.worker.GetRateWorker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val workManager: WorkManager) : ViewModel() {
    private val _currentRate = MutableLiveData<BitcoinRateDTO?>()
    val currentRate: LiveData<BitcoinRateDTO?> = _currentRate

    private val _predict = MutableLiveData<String?>()
    val prediction: LiveData<String?> = _predict



    fun setWork() {
        val rateRequest = OneTimeWorkRequestBuilder<GetRateWorker>().addTag("rate").build()
        val predictionRequest = OneTimeWorkRequestBuilder<BuyWorker>().addTag("predict").build()
        workManager.beginUniqueWork("fetch_rate",ExistingWorkPolicy.REPLACE,rateRequest).then(predictionRequest).enqueue()
        observeData(rateRequest,predictionRequest)
    }

   private fun observeData(rateRequest:WorkRequest, predictionRequest:WorkRequest){
       // Observe Bitcoin Rate Worker
       workManager.getWorkInfoByIdLiveData(rateRequest.id).observeForever { workInfo ->
           if (workInfo != null) {
               if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                   val rate = convertJsonToDTO(workInfo.outputData.getString("bitcoin_rate"))
                   _currentRate.postValue(rate)
               }
           }
       }

       // Observe Prediction Worker
       workManager.getWorkInfoByIdLiveData(predictionRequest.id).observeForever { workInfo ->
           if (workInfo != null) {
               if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                   val prediction = workInfo.outputData.getString("suggestion") ?: "No Data"
                   _predict.postValue(prediction)
               }
           }
       }
   }







    private fun convertJsonToDTO(data: String?): BitcoinRateDTO? {
        return data?.let {
            Gson().fromJson(it, object : TypeToken<BitcoinRateDTO>() {}.type)
        }
    }
}



