package com.HISkyTech.LoginScreen

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.HISkyTech.LoginScreen.databinding.ActivityFoodBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Food : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding
    private lateinit var dialog: Dialog
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.foodadd.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Select Option")

                .setPositiveButton("Add new Task") { dialog, which ->


                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }

            dialog = builder.create()
            dialog.show()

        }


    }

}
