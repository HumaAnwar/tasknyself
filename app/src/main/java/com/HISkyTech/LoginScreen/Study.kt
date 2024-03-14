package com.HISkyTech.LoginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.HISkyTech.LoginScreen.databinding.ActivityStudyBinding

class Study : AppCompatActivity() {
    private lateinit var binding: ActivityStudyBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStudyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            studyadd.setOnClickListener(){
                var builder=AlertDialog.Builder(this@Study)
                builder.setTitle("Select Option")
                    .setPositiveButton("Add Task"){dialog,which->}
                    .setNegativeButton("Cancel"){dialog,which->
                        dialog.dismiss()
                    }
            }




        }
    }
}