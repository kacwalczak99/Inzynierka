package com.example.myfinalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AboutUsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us_screen)

        val back3 = findViewById<ImageView>(R.id.back3)
        back3.setOnClickListener{
            startActivity(Intent(this, MenuScreen::class.java))
        }
    }
}