package com.HISkyTech.LoginScreen.Ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.HISkyTech.LoginScreen.R
import com.HISkyTech.LoginScreen.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var db = Firebase.firestore
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.password
        binding.imageViewEye
        binding.signup.setOnClickListener() {
            startActivity(Intent(this, signup::class.java))
        }


        binding.apply {

            lgn.setOnClickListener {
                if (email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
                    Toast.makeText(this@Login, "Please fill email and password", Toast.LENGTH_SHORT).show()
                } else {
                    db.collection("User")
                        .whereEqualTo("mail", email.text.toString())
                        .whereEqualTo("pasword", password.text.toString())
                        .get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val querySnapshot = task.result
                                if (querySnapshot != null && !querySnapshot.isEmpty) {
                                    val documentId = querySnapshot.documents[0].id

                                    val sharedPreferences = getSharedPreferences("preference", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putBoolean("IsLog",true)
                                    editor.apply()
                                    editor.putString("userId", documentId)
                                    editor.apply()

                                    Toast.makeText(this@Login, "Login Successful", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@Login, MainActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this@Login, "Invalid email or password", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this@Login, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }




        }


    }

    fun togglePasswordVisibility(view: View) {
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Show password
            binding.password.transformationMethod = null
            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24)
        } else {
            // Hide password
            binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24)
        }
    }



}

