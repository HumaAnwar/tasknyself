import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.HISkyTech.LoginScreen.databinding.ActivityLeftBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class left : AppCompatActivity() {
    private lateinit var binding: ActivityLeftBinding
    private val db = Firebase.firestore
    private val PICK_IMAGE_REQUEST_CODE = 123
    private val storageRef = Firebase.storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imagetodo.setOnClickListener {
            val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                uploadImageToFirestore(imageUri)
            }
        }
    }

    private fun uploadImageToFirestore(imageUri: Uri) {
        val imageRef = storageRef.child("images/${imageUri.lastPathSegment}")

        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            // Image uploaded successfully, now get the download URL
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                // Save the download URL to Firestore or perform other actions
                val imageUrl = uri.toString()
                Log.d("MainActivity", "Image URL: $imageUrl")
            }
        }.addOnFailureListener { exception ->
            // Handle any errors that occurred during the upload
            Log.e("MainActivity", "Error uploading image", exception)
        }
    }
}


