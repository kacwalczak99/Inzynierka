package com.example.myfinalapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfinalapp.databinding.ActivityRegisterFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterFragmentBinding


    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener {
            validateData()
        }
        val back6 = findViewById<ImageView>(R.id.back6)
        back6.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    private fun validateData() {
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)) {
            binding.password.error = "Please enter password"
        } else if (password.length < 6) {
            binding.password.error = "Password must be atleast 6 characters long"
        } else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, RegisterData::class.java))
                finish()

            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "SignUp Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}