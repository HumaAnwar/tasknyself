package com.HISkyTech.LoginScreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.HISkyTech.LoginScreen.Models.profile
import com.HISkyTech.LoginScreen.Ui.MainActivity
import com.HISkyTech.LoginScreen.databinding.ActivityProfilemyBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class profilemy: AppCompatActivity() {
    private lateinit var binding: ActivityProfilemyBinding

    private var db=Firebase.firestore
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityProfilemyBinding.inflate(layoutInflater)
            setContentView(binding.root)




            binding.buttonSave.setOnClickListener {
                val updatedProfile =profile()
                updatedProfile.name = binding.entername.text.toString()
                updatedProfile.email = binding.editTextName.text.toString()
                updatedProfile.birthday = binding.editTextDOB.text.toString()


                db.collection("personalTask")

                }
        }
}


