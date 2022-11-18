package com.example.myfinalapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {

    lateinit var handler: Handler
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 2000)
    }
}