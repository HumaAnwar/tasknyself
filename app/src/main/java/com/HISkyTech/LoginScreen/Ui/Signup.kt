package com.HISkyTech.LoginScreen.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.HISkyTech.LoginScreen.databinding.ActivitySignupBinding
import com.HISkyTech.LoginScreen.Models.Loginmodel
import com.HISkyTech.LoginScreen.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var db = Firebase.firestore
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signin.setOnClickListener() {
            startActivity(Intent(this, Login::class.java))
        }
        binding.apply {
            signup.setOnClickListener() {

                var usermodel = Loginmodel()

                if (email.text.toString().isEmpty() && password.text.toString()
                        .isEmpty() && name.text.toString().isEmpty()
                ) {
                    Toast.makeText(this@Signup, "please Fill name and email", Toast.LENGTH_SHORT)
                        .show()
                } else if (password.text.toString().length < 6) {
                    Toast.makeText(this@Signup, "Invalid password format", Toast.LENGTH_SHORT)
                        .show()
                } else if (password.text.toString() != confirmpassword.text.toString()) {
                    Toast.makeText(this@Signup, "password not matched", Toast.LENGTH_SHORT).show()
                } else {
                    usermodel.mail = email.text.toString()
                    usermodel.pasword = password.text.toString()
                    usermodel.name = name.text.toString()
                    db.collection("User").add(usermodel)
                        .addOnSuccessListener { documentreference ->


                            usermodel.userid = documentreference.id
                            db.collection("User").document(documentreference.id).set(usermodel)


                            Toast.makeText(this@Signup, "SignUp Successfull", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this@Signup, Login::class.java))

                        }


                        .addOnFailureListener()
                        {
                            Toast.makeText(this@Signup, "Failed to SignUp", Toast.LENGTH_SHORT)
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

            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24)


        } else {
            // Hide password
            binding.password.transformationMethod = PasswordTransformationMethod.getInstance()

            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24)


        }
    }
    fun toggleVisibility(view: View) {
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Show password


            binding.confirmpassword.transformationMethod = null

            binding.imageEye.setImageResource(R.drawable.baseline_remove_red_eye_24)

        } else {
            // Hide password

            binding.confirmpassword.transformationMethod =
                PasswordTransformationMethod.getInstance()

            binding.imageEye.setImageResource(R.drawable.baseline_remove_red_eye_24)

        }
    }
}

