package com.example.myapplication.data.network

import android.content.SharedPreferences
import com.example.myapplication.extensions.getToken
import com.example.myapplication.extensions.setToken
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    var token: String? = null
        set(value) {
            if (value == null) {
                return
            }
            sharedPreferences.setToken(value)
            field = value
        }
        get() {
            if (field == null) {
                field = sharedPreferences.getToken()
            }
            return field
        }

    var userId: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        if (token == null) {
            return chain.proceed(chain.request())
        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "$token")
            .build()
        return chain.proceed(newRequest)
    }
}