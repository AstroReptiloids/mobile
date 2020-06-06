package com.example.myapplication.ui.base.utils

import android.app.Activity
import android.content.Intent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.login.view.LoginActivity
import javax.inject.Inject

@PerActivity
class Router @Inject constructor() {

    fun navigateLoginActivity(activity: Activity) {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
    }
}