package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.base.utils.Router

class MainActivity : AppCompatActivity() {

    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router = Router()
        findViewById<Button>(R.id.chat_button).setOnClickListener { router.navigateChatActivity(this) }
        findViewById<Button>(R.id.test_button).setOnClickListener { router.navigateLoginActivity(this) }

        title = "Микрочаты"
    }
}
