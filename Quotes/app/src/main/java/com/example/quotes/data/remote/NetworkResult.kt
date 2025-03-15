package com.example.quotes.data.remote

sealed class NetworkResult<T> {
    class Success<T:Any>(val data:T):NetworkResult<T>()
    class Failure<T:Any>(val code: Int, val message: String?):NetworkResult<T>()
    class  Exception<T:Any>(val e:Throwable):NetworkResult<T>()
}