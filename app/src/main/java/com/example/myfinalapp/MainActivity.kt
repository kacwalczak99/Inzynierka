package com.example.myfinalapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfinalapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser();

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging in")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.register.setOnClickListener {
            startActivity(Intent(this, RegisterFragment::class.java))
        }

        binding.login.setOnClickListener {
            validateData()
        }
    }



    private fun validateData() {
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error ="Invalid email fromat"
        }
        else if (TextUtils.isEmpty(password)){
            binding.password.error ="Please enter password"
        }
        else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin(){
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SecondScreen::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this, SecondScreen::class.java))
                finish()
        }

    }
}