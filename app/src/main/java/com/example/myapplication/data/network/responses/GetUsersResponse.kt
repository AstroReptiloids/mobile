package com.example.myapplication.data.network.responses

import com.example.myapplication.data.network.dto.UserDTO

data class GetUsersResponse(
    val data: List<UserDTO>?
) : BaseResponse()