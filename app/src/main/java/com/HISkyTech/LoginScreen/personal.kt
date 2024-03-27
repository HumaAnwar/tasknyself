package com.HISkyTech.LoginScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.compose.ui.layout.FirstBaseline
import androidx.core.net.toUri
import com.HISkyTech.LoginScreen.Models.Loginmodel
import com.HISkyTech.LoginScreen.Models.profile
import com.HISkyTech.LoginScreen.Ui.Login
import com.HISkyTech.LoginScreen.Ui.constant
import com.HISkyTech.LoginScreen.Ui.different_tasks
import com.HISkyTech.LoginScreen.databinding.ActivityMainBinding
import com.HISkyTech.LoginScreen.databinding.ActivityPersonalBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class personal : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalBinding
    private var db = Firebase.firestore
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var selectedImg: Uri

    private lateinit var person:profile
 private var constant=constant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences=getSharedPreferences("preference",Context.MODE_PRIVATE)
selectedImg="".toUri()
        person= profile()
        binding.edibutton.setOnClickListener() {

            val name = findViewById<EditText>(R.id.enterNameEditText).text.toString()
            val email = findViewById<EditText>(R.id.enterEmailEditText).text.toString()
            val dob = findViewById<EditText>(R.id.enterDOBEditText).text.toString()
            val gender =findViewById<RadioGroup>(R.id.genderRadioGroup)
           var gender2=     if (gender.checkedRadioButtonId==R.id.maleRadioButton) "Male" else "Female"


            person.email = email
            person.gndr = gender2
            person.birthday = dob
            person.name = name
       if(email.isEmpty()&&dob.isEmpty()&&name.isEmpty()&&gender2.isEmpty())
       {
           Toast.makeText(this@personal, "fill all fields", Toast.LENGTH_SHORT).show()
       }
            else
       {
           saveImage()
       }




        }

        binding.img.setOnClickListener {
            Toast.makeText(this@personal, "hello", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
             selectedImg = data.data!!
            binding.img.setImageURI(selectedImg)

        }
    }

    private fun savedata()
    {
        db.collection("User").document(sharedPreferences.getString("userId","")!!)
            .set(person)
            .addOnSuccessListener { documentreference ->


                  showtoast(constant.successful)
                startActivity(Intent(this, profilemy::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveImage()
    {

        if(selectedImg.toString().isEmpty())
        {
            showtoast(constant.image)
        }
        else
        {
            val storageRef =
                FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}")
            storageRef.putFile(selectedImg)
                .addOnSuccessListener { taskSnapshot ->
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                      person.image=uri.toString()
                     savedata()
                    }
                }
        }

    }
    fun showtoast(toast:String){

        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()


    }
}