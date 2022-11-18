package com.example.myfinalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myfinalapp.databinding.ActivityDescriptionBinding


class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDescriptionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val goback = findViewById<ImageView>(R.id.back_description)
        goback.setOnClickListener{
            startActivity(Intent(this, SecondScreen::class.java))
        }

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageID = intent.getIntExtra("imageID",R.drawable.a)

        binding.nameProfile.text = name
        binding.descriptionProfile.text = description
        binding.profileImage.setImageResource(imageID)

    }
}