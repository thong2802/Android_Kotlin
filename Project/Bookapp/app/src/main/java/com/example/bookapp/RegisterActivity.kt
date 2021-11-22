package com.example.bookapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bookapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    //data binding
    private lateinit var binding : ActivityRegisterBinding
    // firebase Auth
    private  lateinit var firebaseAuth : FirebaseAuth

    // progress diaglog
    private lateinit var progrssDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        // init progess diaglog while creating new acount | Register
        progrssDialog = ProgressDialog(this)
        progrssDialog.setTitle("Please wait")
        progrssDialog.setCanceledOnTouchOutside(false)

        // hanndler back button click,
         binding.btnback.setOnClickListener {
             onBackPressed() //goto previors screen
         }
        // hanndler  button begin register
        binding.resgisterbtn.setOnClickListener {
            /*  Step
            1> INPUT DATA
            2> validata
            3> creat Acount - firebase Auth
            4> Save data User - Firebase RealTime Databasw*/
            ValidateData()
        }
    }
   private var name = ""
   private var email = ""
   private var password = ""

    private fun ValidateData() {
    // 1> INPUT DATA
        name = binding.nameET.text.toString().trim()
        email = binding.mailregister.text.toString().trim()
        password = binding.passwordRegister.text.toString().trim()
        val cppassword = binding.configpasswordresgister.text.toString().trim()

    //  2> validata
        if (name.isEmpty()){
            //empty Name
            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show()
        }
        else if (email.isEmpty()){
            //empty email
            Toast.makeText(this, "Enter your email...", Toast.LENGTH_SHORT).show()
        }
        else if (password.isEmpty()){
            //empty password
            Toast.makeText(this, "Enter your password...", Toast.LENGTH_SHORT).show()
        }
        else if (cppassword != password){
            //empty password
            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show()
        }else{
            createAccount()
        }
    }
    private fun createAccount() {
        // 3> creat Acount - firebase Auth
        //show progress
        progrssDialog.setMessage("Creating Account...")
        progrssDialog.show()

        //create account Firebase Auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
              // account create, now add user in database
                updateUserInfor()
            }
            .addOnFailureListener { e->
               // fail create
               progrssDialog.dismiss()
                Toast.makeText(this, "Fail create account due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUserInfor() {
        //4> Save data User - Firebase RealTime Databasw
         progrssDialog.setMessage("Saving user info...")
         progrssDialog.show()
        //timetamps
        var timetamps = System.currentTimeMillis()
        //get current user, since user is register so we can get it
        val uid = firebaseAuth.uid

        //setup data to add in database
        val hashMap : HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["mail"] = email
        hashMap["password"] = password
        hashMap["profileImage"] = "" // addd empty, will do in profile edit
        hashMap["userType"] = "user" // possile values are user/admin , will made admin manually on firebase
        hashMap["timetamps"] = timetamps

        // set data to database
        val ref = FirebaseDatabase.getInstance().getReference("User")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                // user infor saved, open dashbroad
                progrssDialog.dismiss()
                Toast.makeText(this, "Acount created..", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, DashBoardUserActivity::class.java))
                finish()
            }
            .addOnFailureListener {e->
                // fail adding to database
                progrssDialog.dismiss()
                Toast.makeText(this, "Fail add to database due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}