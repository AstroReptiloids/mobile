package com.example.myapplication.ui.base.utils

import android.app.Activity
import android.content.Intent
import com.example.myapplication.ui.features.chat.view.ChatActivity
import com.example.myapplication.ui.features.chat.view.ChatListActivity
import com.example.myapplication.ui.features.login.view.LoginActivity
import com.example.myapplication.ui.features.map.view.ChatMapActivity
import com.example.myapplication.ui.features.menu.view.MenuActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor() {

    fun navigateLoginActivity(activity: Activity) {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
    }

    fun navigateChatActivity(activity: Activity) {
        activity.startActivity(Intent(activity, ChatActivity::class.java))
    }

    fun navigateChatActivityByCategory(activity: Activity, categoryId: String) {
        activity.startActivity(Intent(activity, ChatActivity::class.java))
    }

    fun navigateChatListActivityByCategory(activity: Activity, categoryId: String) {
        activity.startActivity(Intent(activity, ChatListActivity::class.java).apply {
            putExtra("id", categoryId)
        })
    }

    fun navigateMenu(activity: Activity) {
        activity.startActivity(Intent(activity, MenuActivity::class.java))
    }

    fun navigateChatMap(activity: Activity) {
        activity.startActivity(Intent(activity, ChatMapActivity::class.java))
    }
}