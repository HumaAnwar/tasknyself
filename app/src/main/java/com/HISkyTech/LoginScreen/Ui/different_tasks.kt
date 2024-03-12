package com.HISkyTech.LoginScreen.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.HISkyTech.LoginScreen.R
import com.HISkyTech.LoginScreen.databinding.ActivityDifferentTasksBinding

class different_tasks : AppCompatActivity() {
    private lateinit var binding: ActivityDifferentTasksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
      binding= ActivityDifferentTasksBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.work.setOnClickListener(){

        }
        binding.home.setOnClickListener(){

        }
        binding.Music.setOnClickListener(){

        }
        binding.food.setOnClickListener(){

        }
        binding.bills.setOnClickListener(){

        }
        binding.study.setOnClickListener(){

        }
        binding.travel.setOnClickListener(){

        }
        binding.shopping.setOnClickListener(){
            
        }
    }
}