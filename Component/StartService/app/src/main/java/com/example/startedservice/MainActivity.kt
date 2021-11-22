package com.example.startedservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start : Button = findViewById(R.id.start)
        var stop : Button = findViewById(R.id.stop)

        start.setOnClickListener {
            var intent : Intent = Intent(this, StartedService::class.java)
            startService(intent)
        }
        stop.setOnClickListener {
            var intent : Intent = Intent(this, StartedService::class.java)
            stopService(intent)
        }

    }
}