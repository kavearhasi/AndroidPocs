package com.example.quotes.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.quotes.data.Worker.FetchWorker
import com.example.quotes.data.repository.QuotesRepoImple
import com.example.quotes.domain.models.Quotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val repository: QuotesRepoImple,
) : ViewModel() {
    init {
        getStoredQuotes()
    }

    private val _q = MutableLiveData<List<Quotes>>()
    val data = _q
    fun setWork() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequestBuilder<FetchWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "Fetch_quote",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun getStoredQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
          //  repository.delete()
            val quotes = repository.getAllQuotes()
            _q.postValue(quotes)
        }
    }
}