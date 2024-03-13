package com.HISkyTech.LoginScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.HISkyTech.LoginScreen.databinding.ActivityWorkBinding

class Work : AppCompatActivity() {
    private lateinit var binding: ActivityWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityWorkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}