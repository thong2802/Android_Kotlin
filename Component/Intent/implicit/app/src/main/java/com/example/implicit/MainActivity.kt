package com.example.implicit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var intent = Intent()
//        intent.setAction(Intent.ACTION_VIEW)
//        intent.setData(Uri.parse(text.text.toString()))
//        startActivity(intent)
        btn.setOnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(text.text.toString()))
            startActivity(intent)
        }
    }
}