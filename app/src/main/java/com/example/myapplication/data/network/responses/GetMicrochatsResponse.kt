package com.example.myapplication.data.network.responses

import com.example.myapplication.data.network.dto.MicrochatDTO

class GetMicrochatsResponse(
    val data: List<MicrochatDTO>?
) : BaseResponse()