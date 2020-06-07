package com.example.myapplication.data.model

import java.io.Serializable

data class UserBO(
    val id: String?,
    val firstName: String?,
    val lastName: String?,
    val login: String?
) : Serializable