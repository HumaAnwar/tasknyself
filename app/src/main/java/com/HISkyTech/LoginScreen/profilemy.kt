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
import com.bumptech.glide.Glide
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


            sharedPreferences=getSharedPreferences("preference",Context.MODE_PRIVATE)
            SetData()



            binding.buttonSave.setOnClickListener {
           startActivity(Intent(this@profilemy,personal::class.java))

                }
        }


    private fun SetData()
    {
        db.collection("User").document(sharedPreferences.getString("userId","")!!)
            .get()
            .addOnSuccessListener()
            {
                document->

                var person=document.toObject(profile::class.java)

                binding.entername.text=person?.name
                binding.mya.text=person?.name
                binding.dob.text=person?.birthday
                binding.editTextName.text=person?.email
                binding.textViewgr.text=person?.gndr

                Glide.with(this@profilemy).load(person?.image).into(binding.imagetodo)

            }
    }
}


