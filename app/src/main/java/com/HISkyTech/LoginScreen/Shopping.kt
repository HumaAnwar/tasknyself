package com.HISkyTech.LoginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.HISkyTech.LoginScreen.databinding.ActivityShoppingBinding

class Shopping : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityShoppingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            val builder=AlertDialog.Builder(this@Shopping)
            builder.setTitle("Select Option")
                .setPositiveButton("Add task"){dialog,which->}
                .setNegativeButton("Cancel"){dialog,which->
                    dialog.dismiss()


                }




        }
    }
}