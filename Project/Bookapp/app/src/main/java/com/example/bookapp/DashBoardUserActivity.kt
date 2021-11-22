package com.example.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.bookapp.databinding.ActivityDashBoardAdminBinding
import com.example.bookapp.databinding.ActivityDashBoardUserBinding
import com.google.firebase.auth.FirebaseAuth

class DashBoardUserActivity : AppCompatActivity() {
    //databinding
    private  lateinit var binding : ActivityDashBoardUserBinding
    // firebase Auth
    private  lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inti firebase AUTH
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handler logout
        binding.logoutbtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
    private fun checkUser() {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
           // not login , user can stay in dashbroad without login too
            binding.subTittleTV.text = "Not Logged In"
        }else{
            //logged in, get and show user info
            val email = firebaseUser.email
            // send to textView of toobar
            binding.subTittleTV.text = email
        }
    }
}