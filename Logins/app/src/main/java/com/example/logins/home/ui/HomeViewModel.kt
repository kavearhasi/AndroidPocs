package com.example.logins.home.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataPref:DataStore<Preferences>) :ViewModel() {

     fun logOut(){
         viewModelScope.launch {
             dataPref.edit {
                 it.clear()
             }
         }
    }
}