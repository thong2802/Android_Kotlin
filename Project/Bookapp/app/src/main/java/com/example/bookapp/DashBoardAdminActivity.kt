package com.example.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.bookapp.databinding.ActivityDashBoardAdminBinding
import com.example.bookapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class DashBoardAdminActivity : AppCompatActivity() {
    //databinding
    private  lateinit var binding : ActivityDashBoardAdminBinding
    // firebase Auth
    private  lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // handler logout
        binding.logoutbtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        //handler click, start add category page
        binding.addCategroryBtn.setOnClickListener {
            startActivity(Intent(this, CategoryAddActivity::class.java))
            finish()
        }

    }

    private fun checkUser() {
        //get current user
        val  firebaseUser = firebaseAuth.currentUser
       if (firebaseUser == null){
          startActivity(Intent(this, MainActivity::class.java))
          finish()
      }else{
          //logged in, get and show user info
          val email = firebaseUser.email
          // send to textView of toobar
          binding.subTittleTV.text = email
      }
    }
}