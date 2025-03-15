package com.example.quotes.data.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.quotes.data.local.QuoteDatabase
import com.example.quotes.data.local.QuotesDao
import com.example.quotes.data.remote.ApiService
import com.example.quotes.data.remote.BaseUrl
import com.example.quotes.data.repository.QuotesRepoImple
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QuoteDatabase =  Room.databaseBuilder(context,
        QuoteDatabase::class.java,
        "quote_db..").build()
    @Singleton
    @Provides 
    fun provideDao(quoteDatabase: QuoteDatabase): QuotesDao {
        return quoteDatabase.getQuoteDao()
    }
    @Singleton
    @Provides
    fun provideWorkManger(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
    @Singleton
    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuotesDao,apiService: ApiService): QuotesRepoImple {
        return QuotesRepoImple( quoteDao,apiService)
    }
}