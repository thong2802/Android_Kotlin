package com.example.bookapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.bookapp.databinding.ActivityLoginBinding
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    //databinding
    private  lateinit var binding : ActivityLoginBinding
    // firebase Auth
    private  lateinit var firebaseAuth : FirebaseAuth

    // progress diaglog
    private lateinit var progrssDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init Firebase  Auth
        firebaseAuth = FirebaseAuth.getInstance()
        // init progress Dialog
        progrssDialog = ProgressDialog(this)
        progrssDialog.setTitle("Please wait..")
        progrssDialog.setCanceledOnTouchOutside(false)

        //handler noAcount
        binding.noAcountTV.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //handler button login
        binding.login.setOnClickListener {
            /*
            1/ input data
            2/ valid data
            3/ login - firebase auth
            4/ check user tyfe -firebase auth
                 - if User - move drashbroad User
                 - if Admin - move drashbroad Admin
             */
            ValidateData()
        }
    }
    private var email : String = ""
    private var password : String = ""
    private fun ValidateData() {
        //  1/ input data
        email = binding.mail.text.toString().trim()
        password = binding.password.text.toString().trim()
        //2/ valid data
        if (email.isEmpty()){
            //empty email
            Toast.makeText(this, "Enter your email...", Toast.LENGTH_SHORT).show()
        }
        if (password.isEmpty()){
            //empty email
            Toast.makeText(this, "Enter your password...", Toast.LENGTH_SHORT).show()
        }
        else{
            loginUser()
        }
    }

    private fun loginUser() {
    // 3/ login - firebase auth
        // show progress
        progrssDialog.setMessage("Logging In...")
        progrssDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    checkUser()
                }
                .addOnFailureListener {e->
                    // fail login
                    progrssDialog.dismiss()
                    Toast.makeText(this, "Fail login account due to ${e.message}", Toast.LENGTH_SHORT).show()
                 }
    }

    private fun checkUser() {
        //   4/ check user tyfe -firebase auth
        //          - if User - move drashbroad User
        //          - if Admin - move drashbroad Admin
        progrssDialog.setMessage("Checking User...")
        progrssDialog.show()
        val firebaseUser = firebaseAuth.currentUser!!
        val ref = FirebaseDatabase.getInstance().getReference("User")
        ref.child(firebaseUser.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        progrssDialog.dismiss()
                        //get user type : user/admin
                        val userType = snapshot.child("userType").value
                        if (userType == "user"){
                            //open dashbroad user
                            startActivity(Intent(this@LoginActivity, DashBoardUserActivity::class.java))
                            finish()
                        }else if (userType == "admin"){
                            //open dashbroad admin
                            startActivity(Intent(this@LoginActivity, DashBoardAdminActivity::class.java))
                            finish()
                        }
                     }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }
}