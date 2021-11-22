package com.example.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SplashActivity : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       firebaseAuth = FirebaseAuth.getInstance()

       Handler().postDelayed(Runnable {
           checkUser()
           //startActivity(Intent(this, MainActivity::class.java))
          // finish()
       }, 2000)
    }

    private fun checkUser() {
        //get current user, if logged in or not
        var firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            // user not logged in, go to main screen
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
           //  User logged in, check type user
            val firebaseUser = firebaseAuth.currentUser!!
            val ref = FirebaseDatabase.getInstance().getReference("User")
            ref.child(firebaseUser.uid)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            //get user type : user/admin
                            val userType = snapshot.child("userType").value
                            if (userType == "user"){
                                //open dashbroad user
                                startActivity(Intent(this@SplashActivity, DashBoardUserActivity::class.java))
                                finish()
                            }else if (userType == "admin"){
                                //open dashbroad admin
                                startActivity(Intent(this@SplashActivity, DashBoardAdminActivity::class.java))
                                finish()
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
        }
    }
}

/*
Keep user logged in
1/ Check user logged in
2/ Check user or admin
 */