package com.example.myapplication.extensions

import android.content.SharedPreferences

fun SharedPreferences.getToken(): String? {
    return getString("token_", null)
}

fun SharedPreferences.setToken(token: String?) {
    edit().putString("token_", token).apply()
}