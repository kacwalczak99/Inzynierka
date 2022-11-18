package com.example.myfinalapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class BugReportScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_report_screen)

        val recipient = findViewById<EditText>(R.id.recipientbug)
        val subject = findViewById<EditText>(R.id.subjectbug)
        val message = findViewById<EditText>(R.id.messagebug)

        val goback = findViewById<ImageView>(R.id.back_bug)
        goback.setOnClickListener{
            startActivity(Intent(this, SettingsScreen::class.java))
        }

        val sendemail = findViewById<Button>(R.id.sendemail)
        sendemail.setOnClickListener {
            val recipient = recipient.text.toString().trim()
            val subject = subject.text.toString().trim()
            val message = message.text.toString().trim()

            sendEmail(recipient, subject, message)
        }
    }


    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:kacwalczak99@outlook.com")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}