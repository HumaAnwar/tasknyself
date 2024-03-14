package com.HISkyTech.LoginScreen

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.HISkyTech.LoginScreen.Models.food_task
import com.HISkyTech.LoginScreen.Models.task_model
import com.HISkyTech.LoginScreen.databinding.ActivityFoodBinding
import com.HISkyTech.LoginScreen.databinding.ActivityHomeBinding
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
