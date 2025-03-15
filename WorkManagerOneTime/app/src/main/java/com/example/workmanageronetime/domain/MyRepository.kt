package com.example.workmanageronetime.domain

import com.example.workmanageronetime.data.remote.BitcoinApi
import com.example.workmanageronetime.data.remote.BitcoinRateDTO
import com.example.workmanageronetime.data.remote.EmailVerificationApi
import com.example.workmanageronetime.data.remote.EmailVerificationDTO
import com.example.workmanageronetime.data.remote.EmojiApi
import com.example.workmanageronetime.data.remote.EmojiDTO
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class MyRepository @Inject constructor( private val api: BitcoinApi,private val verifyApi:EmailVerificationApi,private val emojiApi: EmojiApi) {
   suspend fun getRate(): Response<BitcoinRateDTO> {
          return api.getbitcoinPrice()
   }

     fun isValidEmail(email:String): Response<EmailVerificationDTO> {
        return verifyApi.verifyEmail(email = email)
    }

    suspend fun getEmoji(): Response<EmojiDTO> {
        delay(30000)
        return emojiApi.getEmoji()
    }

}