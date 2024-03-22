package com.HISkyTech.LoginScreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.HISkyTech.LoginScreen.Ui.MainActivity
import com.HISkyTech.LoginScreen.databinding.ActivityProfilemyBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class profilemy: AppCompatActivity() {
    private lateinit var binding: ActivityProfilemyBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var db=Firebase.firestore
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityProfilemyBinding.inflate(layoutInflater)
            setContentView(binding.root)

            sharedPreferences = getSharedPreferences("my_preference", Context.MODE_PRIVATE)

            // Retrieve values from SharedPreferences and set them to the views
            val name = sharedPreferences.getString("name", "")
            val email = sharedPreferences.getString("email", "")
            val dob = sharedPreferences.getString("dob", "")

            binding.entername.text = name
            binding.editTextName.text = email
            binding.editTextDOB.text = dob

            // Set onClickListener for the save button
            binding.buttonSave.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.putString("name", binding.entername.text.toString())
                editor.putString("email", binding.editTextName.text.toString())
                editor.putString("dob", binding.editTextDOB.text.toString())
                editor.apply()
                Toast.makeText(this, "Edit Profile", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@profilemy,personal::class.java))


                }
        }
}


