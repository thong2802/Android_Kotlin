package com.example.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //view binding
    private  lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // lets check quick document databinding

        // handler login
        binding.btnlogin.setOnClickListener {
          startActivity(Intent(this, LoginActivity::class.java))
        }
        //handler skip
        binding.btncontinue.setOnClickListener {
           startActivity(Intent(this, DashBoardUserActivity::class.java))
        }
    }
}