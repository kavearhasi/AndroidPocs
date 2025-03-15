package com.example.logins.main

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel

import com.example.logins.remote.DataStoreKeys

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val dataPrefUtil: PrefDataStoreUtil
private val dataStore: DataStore<Preferences>
) : ViewModel() {

    suspend fun getUserDetails():Boolean {
      val userName = dataStore.data.map {preferences ->
          preferences[stringPreferencesKey("userName")] ?: ""
      }.first()

      val password = dataStore.data.map { preferences ->
          preferences[stringPreferencesKey("password")] ?: ""
      }.first()
      val res:Boolean = (userName.isNotEmpty() && password.isNotEmpty())
      println("result- ${res.toString()}")
      return (userName.isNotEmpty() && password.isNotEmpty())
      }


}