package com.example.myfinalapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myfinalapp.databinding.ActivityProfileScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class ProfileScreen : AppCompatActivity() {

    private lateinit var binding: ActivityProfileScreenBinding
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var selectedImg: Uri
    private lateinit var dialog: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        dialog = AlertDialog.Builder(this)
            .setMessage("Updating")
            .setCancelable(false)


        binding.imageView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        binding.continueBtn.setOnClickListener {
            if (selectedImg == null) {
                Toast.makeText(this, "Please select your Img", Toast.LENGTH_LONG).show()
            }else uploadData()
        }

        val back5 = findViewById<ImageView>(R.id.back5)
        back5.setOnClickListener {
            startActivity(Intent(this, SecondScreen::class.java))
        }
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        firebaseAuth = FirebaseAuth.getInstance()

        val uid = firebaseAuth.currentUser?.uid

        database = FirebaseDatabase.getInstance().getReference("Users/")

        if (uid != null) {
            database.child(uid).get().addOnSuccessListener {

                if (it.exists()) {

                    val name = it.child("name").value
                    val surname = it.child("surname").value
                    val phone_number = it.child("phone_number").value

                    binding.tvName.text = name.toString().capitalize()
                    binding.tvSurname.text = surname.toString().capitalize()
                    binding.tvPhoneNumber.text = phone_number.toString()

                } else {
                }
            }
        }
    }

    private fun uploadData() {
        val reference = storage.reference.child("Profile").child(Date().time.toString())
        reference.putFile(selectedImg).addOnCompleteListener{
            if (it.isSuccessful){
                reference.downloadUrl.addOnSuccessListener { task ->
                    uploadInfo(task.toString())
                }
            }
        }
    }

    private fun uploadInfo(imageUrl: String) {
        val user = User(binding.tvName.text.toString(),binding.tvSurname.text.toString(),
            binding.tvPhoneNumber.text.toString(),imageUrl)


        firebaseDatabase.reference.child("Users")
            .child(firebaseAuth.uid.toString())
            .setValue(user)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (data.data != null) {
                selectedImg = data.data!!
                binding.imageView.setImageURI(selectedImg)
            }

        }

    }


}
