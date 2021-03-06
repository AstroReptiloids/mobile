package com.example.myapplication.data.network.dto

import com.example.myapplication.data.model.UserBO
import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val login: String?
) {

    fun toBO(): UserBO {
        return UserBO(
            id = id,
            firstName = firstName,
            lastName = lastName,
            login = login
        )
    }

    companion object {

        fun fromBO(userBO: UserBO?): UserDTO? {
            if(userBO == null){
                return null
            }
            return UserDTO(
                id = userBO.id,
                firstName = userBO.firstName,
                lastName = userBO.lastName,
                login = userBO.login
            )
        }


    }
}