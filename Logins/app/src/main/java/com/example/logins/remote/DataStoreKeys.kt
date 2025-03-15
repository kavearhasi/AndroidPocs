package com.example.logins.remote

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val PREF_KEY_USERNAME  = stringPreferencesKey("userName")
    val PREF_KEY_PASSWORD = stringPreferencesKey("password")

}