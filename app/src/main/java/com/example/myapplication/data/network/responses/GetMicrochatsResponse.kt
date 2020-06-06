package com.example.myapplication.data.network.responses

import com.example.myapplication.data.network.dto.MicrochatDTO

class GetMicrochatsResponse(
    val data: Data?
) : BaseResponse(){

    data class Data(
        val microchats: List<MicrochatDTO>?
    )
}