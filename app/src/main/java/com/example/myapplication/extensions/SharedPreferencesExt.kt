package com.example.myapplication.extensions

import android.content.SharedPreferences

fun SharedPreferences.getToken(): String? {
    return getString("token_", null)
}

fun SharedPreferences.setToken(token: String?) {
    edit().putString("token_", token).apply()
}

fun SharedPreferences.getUserId(): String? {
    return getString("userId_", null)
}

fun SharedPreferences.setUserId(token: String?) {
    edit().putString("token_", token).apply()
}