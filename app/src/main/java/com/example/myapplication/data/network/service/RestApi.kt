package com.example.myapplication.data.network.service

import com.example.myapplication.data.network.requests.CreateMicrochatRequest
import com.example.myapplication.data.network.requests.LoginRequest
import com.example.myapplication.data.network.requests.SendMessageRequest
import com.example.myapplication.data.network.responses.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestApi {

    @GET("/users")
    fun getUsers(): Call<GetUsersResponse>

    @GET("/categories")
    fun getChatCategories(): Call<GetChatCategoriesResponse>

    @GET("/microchats")
    fun getMicrochats(@Query("parent_id") parentId: String?, @Query("category_id") categoryId: String?): Call<GetMicrochatsResponse>

    @GET("/messages")
    fun getMessages(@Query("microchat_id") microchatId: String?): Call<GetMessagesResponse>

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/messages")
    fun sendMessage(@Body sendMessageRequest: SendMessageRequest): Call<SendMessageResponse>

    @POST("/microchats")
    fun createMicrochat(@Body createMicrochatRequest: CreateMicrochatRequest): Call<CreateMicrochatResponse>
}