package com.example.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var btn : Button = findViewById(R.id.back);
        btn.setOnClickListener {
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }
    }
}