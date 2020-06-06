package com.example.myapplication.data.network.service

import com.example.myapplication.data.network.responses.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApi {

    @GET("/users")
    fun getUsers(): Call<GetUsersResponse>

    @GET("/categories")
    fun getChatCategories(): Call<GetChatCategoriesResponse>

    @GET("/microchats")
    fun getMicrochats(): Call<GetMicrochatsResponse>

    @GET("/messages")
    fun getMessages(): Call<GetMessagesResponse>

    @POST("/auth/login")
    fun login(@Body login: String, @Body password: String): Call<LoginResponse>
}