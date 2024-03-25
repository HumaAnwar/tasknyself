import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.HISkyTech.LoginScreen.Models.profile
import com.HISkyTech.LoginScreen.databinding.ActivityLeftBinding
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class left : AppCompatActivity() {
    private lateinit var binding: ActivityLeftBinding
    private val db = Firebase.firestore
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences("preference", Context.MODE_PRIVATE)

        db.collection("User").document(sharedPreferences.getString("userId","")!!)
            .get()
            .addOnSuccessListener()
            {
                    document->

                var person=document.toObject(profile::class.java)
                Toast.makeText(this@left, "sdfkaak", Toast.LENGTH_SHORT).show()

                Glide.with(this@left).load(person?.image).into(binding.imagetodo)

            }
    }

}


