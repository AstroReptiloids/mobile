package com.example.myapplication.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {

    var token: String? = "ya_vadik_a_ne_huy"

    override fun intercept(chain: Interceptor.Chain): Response {
        if (token == null) {
            return chain.proceed(chain.request())
        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}