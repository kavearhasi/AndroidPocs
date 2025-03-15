package com.example.workmanageronetime.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.workmanageronetime.data.remote.BaseUrl
import com.example.workmanageronetime.data.remote.BitcoinApi
import com.example.workmanageronetime.data.remote.EmailVerificationApi
import com.example.workmanageronetime.data.remote.EmojiApi
import com.example.workmanageronetime.domain.MyRepository
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
    fun provideRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideBitcoinApi(retrofit: Retrofit): BitcoinApi {
        return retrofit.create(BitcoinApi::class.java)
    }
    @Provides
    fun provideEmailVerificationApi(retrofit: Retrofit):EmailVerificationApi{
        return retrofit.create(EmailVerificationApi::class.java)
    }

    @Provides
    fun provideRepo( api:BitcoinApi,verifyApi:EmailVerificationApi,emojiApi: EmojiApi): MyRepository {
        return MyRepository(api,verifyApi,emojiApi)
    }
    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
       return WorkManager.getInstance(context)
    }

    @Provides
    fun provideEmojiApi(retrofit: Retrofit):EmojiApi
    {
        return retrofit.create(EmojiApi::class.java)
    }
}
