package com.HISkyTech.LoginScreen.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.HISkyTech.LoginScreen.databinding.ActivitySignupBinding
import com.HISkyTech.LoginScreen.Models.loginmodel
import com.HISkyTech.LoginScreen.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var db = Firebase.firestore
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.confirmpassword
        binding.password
        binding.imageViewEye
        binding.imageViewEye
        binding.signin.setOnClickListener() {
            startActivity(Intent(this, Login::class.java))
        }
        binding.apply {
            signup.setOnClickListener() {

                var usermodel = loginmodel()

                if (email.text.toString().isEmpty() && password.text.toString()
                        .isEmpty() && name.text.toString().isEmpty()
                ) {
                    Toast.makeText(this@signup, "please Fill name and email", Toast.LENGTH_SHORT)
                        .show()
                } else if (password.text.toString().length < 6) {
                    Toast.makeText(this@signup, "Invalid password format", Toast.LENGTH_SHORT)
                        .show()
                } else if (password.text.toString() != confirmpassword.text.toString()) {
                    Toast.makeText(this@signup, "password not matched", Toast.LENGTH_SHORT).show()
                } else {
                    usermodel.mail = email.text.toString()
                    usermodel.pasword = password.text.toString()
                    usermodel.name = name.text.toString()
                    db.collection("User").add(usermodel)
                        .addOnSuccessListener { documentreference ->


                            usermodel.userid = documentreference.id
                            db.collection("User").document(documentreference.id).set(usermodel)


                            Toast.makeText(this@signup, "SignUp Successfull", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this@signup, Login::class.java))

                        }


                        .addOnFailureListener()
                        {
                            Toast.makeText(this@signup, "Failed to SignUp", Toast.LENGTH_SHORT)
                                .show()
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
            binding.confirmpassword.transformationMethod = null
            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24)
            binding.imageEye.setImageResource(R.drawable.baseline_remove_red_eye_24)

        } else {
            // Hide password
            binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.confirmpassword.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24)
            binding.imageEye.setImageResource(R.drawable.baseline_remove_red_eye_24)

        }
    }
}

