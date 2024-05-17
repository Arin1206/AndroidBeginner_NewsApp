package com.example.myapp_release

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class About : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val tvDataReceived: TextView = findViewById(R.id.namadicoding)
        val tvDataReceived2: TextView = findViewById(R.id.emaildicoding)
        val name = intent.getStringExtra(EXTRA_NAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val text = "$name"
        tvDataReceived.text = text
        val text2 = "$email"
        tvDataReceived2.text = text2
    }
}