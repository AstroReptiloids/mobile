package com.example.myapplication.ui.base.view

import androidx.annotation.StringRes

interface IView {

    fun hideProgress()

    fun showProgress()

    fun showToast(message: CharSequence)

    fun showToast(@StringRes stringId: Int)
}