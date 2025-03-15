package com.example.logins.dependencyinject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.logins.login.data.LoginApi
import com.example.logins.login.data.LoginRepository
import com.example.logins.remote.BaseUrl
import com.example.logins.remote.PrefDataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "Logins")
@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideLoginApi(): LoginApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    fun provideLoginRepository(api: LoginApi): LoginRepository {
        return LoginRepository(api)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
//        return PreferenceDataStoreFactory.create(
//            corruptionHandler = ReplaceFileCorruptionHandler(
//                produceNewData = { emptyPreferences() }
//            ),
//            produceFile = { context.preferencesDataStoreFile("Logins") }
//        )
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideDatastorePrefUtil(ds: DataStore<Preferences>): PrefDataStoreUtil{
        return PrefDataStoreUtil(ds)
    }

}

