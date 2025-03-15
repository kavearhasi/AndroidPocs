package com.example.logins.remote

import com.example.logins.login.data.userinfo.User

sealed class NetworkResult {
    class Success(val data: User): NetworkResult()
    class Failure(val errorCode:Int,val message:String): NetworkResult()
   class Exception(val e:Throwable): NetworkResult()

}