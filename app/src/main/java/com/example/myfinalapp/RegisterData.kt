package com.example.myfinalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.myfinalapp.databinding.ActivityRegisterDataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterData : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterDataBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")


        binding.saveBtn.setOnClickListener{
            val username = binding.etName.text.toString()
            val usersurname = binding.etSurname.text.toString()
            val userphone_number = binding.etPhoneNumber.text.toString()

            val user = User(username, usersurname, userphone_number)

            if (uid != null){

                databaseReference.child(uid).setValue(user).addOnSuccessListener{



                    Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, SecondScreen::class.java))

                }.addOnFailureListener{
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }

            }
        }}}