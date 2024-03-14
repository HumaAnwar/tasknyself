package com.HISkyTech.LoginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.HISkyTech.LoginScreen.databinding.ActivityBillsBinding

class Bills : AppCompatActivity() {
    private lateinit var binding: ActivityBillsBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityBillsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            billadd.setOnClickListener(){
                var builder=AlertDialog.Builder(this@Bills)
                builder.setTitle("Select Option")
                    .setPositiveButton("Add Task"){dialog,which->}
                    .setNegativeButton("Cancel"){dialog,which->
                        dialog.dismiss()
                    }
            }




        }
    }
}