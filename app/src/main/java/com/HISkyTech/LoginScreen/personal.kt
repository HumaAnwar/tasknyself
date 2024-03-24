package com.HISkyTech.LoginScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
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
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data
import java.util.UUID

class personal : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("my_preference", Context.MODE_PRIVATE)



        binding.edibutton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("name", binding.enterNameEditText.text.toString())
            editor.putString("Name", binding.myAccountText.text.toString())
            editor.putString("email", binding.enterEmailEditText.text.toString())
            editor.putString("dob", binding.enterDOBEditText.text.toString())

            val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
            val selectedGenderId = genderRadioGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId)
            editor.putString("gender", selectedGender.text.toString())

            editor.apply()

            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@personal, profilemy::class.java))
        }



        binding.img.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImg = data.data!!
            binding.img.setImageURI(selectedImg)

            val storageRef = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}")
            storageRef.putFile(selectedImg)
                .addOnSuccessListener { taskSnapshot ->
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()

                        val editor = sharedPreferences.edit()
                        editor.putString("imageurl",imageUrl)
                        editor.apply()

                        // You can save the image URL to SharedPreferences or Firebase Realtime Database/Firestore
                    }
                }
        }
    }
}

