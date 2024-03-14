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
                    add_foodtask()

                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }

            dialog = builder.create()
            dialog.show()

        }


    }

    private fun add_foodtask() {

        var dialog = Dialog(this)
        dialog.setContentView(R.layout.food_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.setCancelable(false)
        var ttle = dialog.findViewById<EditText>(R.id.food_name)
        var desc = dialog.findViewById<EditText>(R.id.food_des)
        var dat = dialog.findViewById<EditText>(R.id.fdate)
        var quantity = dialog.findViewById<EditText>(R.id.quantity)
        var cback = dialog.findViewById<ImageView>(R.id.fback)
        var foodadd = dialog.findViewById<Button>(R.id.foodbtn)

        cback.setOnClickListener()
        {
            dialog.dismiss()
        }
        foodadd.setOnClickListener() {
            if (ttle.text.toString().isEmpty() || desc.text.toString().isEmpty() ||
                dat.text.toString().isEmpty() || quantity.text.isEmpty()
            ) {
                Toast.makeText(this, "Please Enter All fields", Toast.LENGTH_SHORT).show()
            } else {
                val food_model = food_task()
                food_model.ftitle = ttle.text.toString()
                food_model.fdescription = desc.text.toString()
                food_model.ftask_date = dat.text.toString()
                food_model.fquantity = quantity.text.toString()
                val sharedPreferences = getSharedPreferences("preference", Context.MODE_PRIVATE)

                db.collection("Food_Tasks").add(food_model)
                    .addOnSuccessListener { doc ->
                        food_model.task_id = doc.id
                        food_model.user_id = sharedPreferences.getString("userId", "").toString()
                        db.collection("Food_Tasks").document(doc.id).set(food_model)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT)
                                    .show()

                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Task not added", Toast.LENGTH_SHORT).show()
                            }

                    }
            }
            dialog.dismiss()
        }

                dialog.show()
            }
        }
