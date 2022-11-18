package com.example.myfinalapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_screen)


        val switch: Switch = findViewById(R.id.theme)

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                setDefaultNightMode(MODE_NIGHT_NO)
            }
        }


        val bugbtn = findViewById<Button>(R.id.bugbtn)
        bugbtn.setOnClickListener {
            startActivity(Intent(this, BugReportScreen::class.java))
        }


        val back2 = findViewById<ImageView>(R.id.back2)
        back2.setOnClickListener {
            startActivity(Intent(this, MenuScreen::class.java))
        }
        }
    }
