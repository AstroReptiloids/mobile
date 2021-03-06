package com.example.myapplication.data.exceptions

import android.util.Log

class InternalServerException(
    val responseCode: Int,
    override val message: String?
) : RuntimeException() {

    init {
        Log.e(
            javaClass.simpleName,
            this.responseCode.toString() + " : " + this.message
        )
    }
}