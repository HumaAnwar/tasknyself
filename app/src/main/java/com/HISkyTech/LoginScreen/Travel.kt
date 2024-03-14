package com.HISkyTech.LoginScreen

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.HISkyTech.LoginScreen.databinding.ActivityTravelBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Travel : AppCompatActivity() {
    private lateinit var binding: ActivityTravelBinding
    private lateinit var dialog: AlertDialog
    private var db=Firebase.firestore
    private lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityTravelBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            traveladd.setOnClickListener(){
                val builder=AlertDialog.Builder(this@Travel)
                builder.setTitle("Select Option")
                    .setPositiveButton("Add Task")  { dialog , which ->





                    }
                    .setNegativeButton("Cancel") {dialog ,which ->
                        dialog.dismiss()
                    }
            }












        }
    }
}