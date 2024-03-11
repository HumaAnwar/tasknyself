import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.HISkyTech.LoginScreen.databinding.ActivityLeftBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class left : AppCompatActivity() {
    private lateinit var binding: ActivityLeftBinding
    private val db = Firebase.firestore
    private lateinit var selectedimg : Uri
    private lateinit var dialog: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog=AlertDialog.Builder(this)
            .setMessage("Updating profile")
            .setCancelable(false)

        binding.imagetodo.setOnClickListener() {
            Toast.makeText(this, "hdfyg", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode,data)

        if(data!=null) {
            if (data.data != null) {
                selectedimg = data.data!!
                binding.imagetodo.setImageURI(selectedimg)
            }
        }}}