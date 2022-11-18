package com.example.myfinalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class RegulationsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regulations_screen)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener{
            startActivity(Intent(this, MenuScreen::class.java))
        }
    }
}