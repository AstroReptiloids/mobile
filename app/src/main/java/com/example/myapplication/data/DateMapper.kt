package com.example.myapplication.data

import java.text.SimpleDateFormat
import java.util.*

object DateMapper {
    var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    fun parseDate(string: String?): Date? {
        if (string == null) {
            return null
        }
        return format.parse(string)
    }
}