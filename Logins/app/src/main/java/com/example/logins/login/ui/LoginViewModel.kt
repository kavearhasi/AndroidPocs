package com.example.logins.login.ui

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logins.login.data.LoginRepository
import com.example.logins.login.data.userinfo.LoginRequest
import com.example.logins.remote.DataStoreKeys

import com.example.logins.remote.NetworkResult
import com.example.logins.remote.PrefDataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: LoginRepository,
//   private val dataPrefUtil:PrefDataStoreUtil
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private var _authResponse: NetworkResult? = null
    private val results = MutableLiveData<String>()
    val authResponse: LiveData<String> get() = results
    fun checkUserAuth(loginRequest: LoginRequest) {
        Log.e("model", "reached viewmodel")
        viewModelScope.launch {
            _authResponse = repo.getUser(loginRequest)
            Log.e("model", "job completed entering check")
            checkResults()
        }


    }

    private fun checkResults() {
        println("delivered responce $_authResponse")
        when (val response = _authResponse) {
            is NetworkResult.Exception -> {
                Log.e(
                    "LoginViewModel",
                    "Exception while verifying the authentication of user \n error message :${response.e.message}"
                )
                results.value = "_exception"
            }

            is NetworkResult.Failure -> {
                results.value = response.message
            }

            is NetworkResult.Success -> {
                results.value = "_success"
            }

            null -> {
                results.value = "_unknown"

            }
        }
    }

    suspend fun storeLogins(name: String, password: String) {
//        dataPrefUtil.setValues(DataStoreKeys.PREF_KEY_USERNAME,name)
//        dataPrefUtil.setValues(DataStoreKeys.PREF_KEY_PASSWORD,password)

        dataStore.edit {
            it[DataStoreKeys.PREF_KEY_USERNAME]=name
            it[DataStoreKeys.PREF_KEY_PASSWORD]=password
        }
    }

}