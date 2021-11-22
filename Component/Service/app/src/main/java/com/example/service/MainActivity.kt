package com.example.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start.setOnClickListener {
            clickStartService()
        }
        stop.setOnClickListener {
            clickStopService()
        }
    }
    private fun clickStartService() {
       val intent : Intent = Intent(this, Service::class.java)
        intent.putExtra("key_data_intent", text.text.toString().trim())
        startActivity(intent)
    }
    private fun clickStopService() {
        val intent : Intent = Intent(this, Service::class.java)
        stopService(intent)
    }
}