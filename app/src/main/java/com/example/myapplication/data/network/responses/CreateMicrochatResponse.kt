package com.example.myapplication.data.network.responses

import com.example.myapplication.data.network.dto.MicrochatDTO
import com.google.gson.annotations.SerializedName

data class CreateMicrochatResponse(
    @SerializedName("data")
    val microchatDTO: MicrochatDTO
) : BaseResponse()