package com.example.myfinalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.myfinalapp.databinding.ActivityMenuScreenBinding

lateinit var binding:ActivityMenuScreenBinding

class MenuScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val aboutus = findViewById<TextView>(R.id.aboutus)
        aboutus.setOnClickListener {
            startActivity(Intent(this, AboutUsScreen::class.java))
        }
        val regulations = findViewById<TextView>(R.id.regulations)
        regulations.setOnClickListener {
            startActivity(Intent(this, RegulationsScreen::class.java))
        }
        val settings = findViewById<TextView>(R.id.settings)
        settings.setOnClickListener {
            startActivity(Intent(this, SettingsScreen::class.java))
        }
        val back4 = findViewById<ImageView>(R.id.back4)
        back4.setOnClickListener{
            startActivity(Intent(this, SecondScreen::class.java))
        }
    }
}