package com.HISkyTech.LoginScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.compose.ui.layout.FirstBaseline
import com.HISkyTech.LoginScreen.Models.Loginmodel
import com.HISkyTech.LoginScreen.Models.profile
import com.HISkyTech.LoginScreen.Ui.Login
import com.HISkyTech.LoginScreen.Ui.different_tasks
import com.HISkyTech.LoginScreen.databinding.ActivityMainBinding
import com.HISkyTech.LoginScreen.databinding.ActivityPersonalBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class personal : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalBinding
private var db=Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edibutton.setOnClickListener() {

            val name = findViewById<EditText>(R.id.enterNameEditText).text.toString()
            val email = findViewById<EditText>(R.id.enterEmailEditText).text.toString()
            val dob = findViewById<EditText>(R.id.enterDOBEditText).text.toString()
            val gender =
                if (findViewById<RadioButton>(R.id.maleRadioButton).isChecked) "Male" else "Female"

            var person = profile()
            person.email = email
            person.gndr = gender
            person.birthday = dob
            person.name = name
            db.collection("personalTask").add(person)
                .addOnSuccessListener { documentreference ->


                    person.userId = documentreference.id
                    db.collection("personalTask").document(documentreference.id).set(person)


                    Toast.makeText(this, "Add information successful", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, profilemy::class.java))
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }

        }

        binding.img.setOnClickListener {
            Toast.makeText(this@personal, "jsdhsldj", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

    }
    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
           var selectedImg = data.data!!
            binding.img.setImageURI(selectedImg)
  val storageRef = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}")
            storageRef.putFile(selectedImg)
                .addOnSuccessListener { taskSnapshot ->
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        var db = Firebase.firestore
                    }}}}}