package com.HISkyTech.LoginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.HISkyTech.LoginScreen.databinding.ActivityWorkBinding

class Work : AppCompatActivity() {
    private lateinit var binding: ActivityWorkBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityWorkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
         binding.apply {
             workadd.setOnClickListener(){
                  val builder =AlertDialog.Builder(this@Work)
                 builder.setTitle("Select Option")
                     .setPositiveButton("Add Task"){dialog,which ->

                     }
                     .setNegativeButton("Cancel"){dialog,which->
                         dialog.dismiss()
                     }



             }





         }
    }
}