package com.example.myapplication.ui.base.utils

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}