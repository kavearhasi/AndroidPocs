package com.example.logins.remote

import android.util.Log
import com.example.logins.login.data.userinfo.User
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<User>
): NetworkResult {
    return try {
        val response = execute()
        val data = response.body()
        val responseMessage = checkCode(response.code())
        if (responseMessage == "Success" && data != null) {
            Log.e("handle","reached succes generated")
            NetworkResult.Success(data)

        } else {
            Log.e("handle","reached failure generated")
            NetworkResult.Failure(response.code(), responseMessage)
        }

    } catch (e: Exception) {
        NetworkResult.Exception(e)
    }
}

fun checkCode(code: Int): String {
    return when (code) {
        200, 201, 202 -> {
            "Success"
        }

        204 -> {
            "No Content"
        }

        400 -> {
            "Bad Request"
        }

        401 -> {
            "Unauthorized"
        }

        403 -> {
            "Forbidden"
        }

        404 -> {
            "Not Found"
        }

        405 -> {
            "Method Not Allowed"
        }

        429 -> {
            "Too Many Requests"
        }

        500 -> {
            "Internal Server Error"
        }

        503 -> {
            "Service Unavailable"
        }

        else -> {
            "Unrecognised Response"
        }
    }
}