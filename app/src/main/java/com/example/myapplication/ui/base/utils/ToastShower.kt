package com.example.myapplication.ui.base.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import java.lang.ref.WeakReference
import javax.inject.Inject

class ToastShower @Inject constructor(context: Context) {

    private val contextRef = WeakReference(context)

    private var currentToast: Toast? = null

    private val toastHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.obj is String) {
                showToast(msg.obj as CharSequence)
            }
        }
    }

    fun showToast(text: CharSequence) {
        if (contextRef.get() == null) {
            return
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            currentToast?.cancel()
            currentToast = Toast.makeText(contextRef.get(), text, Toast.LENGTH_SHORT)
            currentToast?.show()
        } else {
            Message.obtain(toastHandler, 0, text).sendToTarget()
        }
    }

    fun showToastToQueue(message: String) {
        if (contextRef.get() == null) {
            return
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(contextRef.get(), message, Toast.LENGTH_SHORT).show()
        } else {
            Message.obtain(toastHandler, 0, message).sendToTarget()
        }

    }
}