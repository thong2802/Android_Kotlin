package com.example.explicit
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class SecondActivity : AppCompatActivity() {
    companion object{
        public val ResulutCode : Int = 12
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var btncaculator : Button = findViewById(R.id.caculator)
        var textA : TextView = findViewById(R.id.textA)
        var textB : TextView = findViewById(R.id.textB)
//        var users : TextView = findViewById(R.id.user)
//        var intent : Intent = getIntent()
//
//        var numA : Int = intent.getIntExtra("A", 0)
//        var numB : Int = intent.getIntExtra("B", 0)
//        var user : User = intent.getSerializableExtra("User") as User
//        textA.setText(numA.toString())
//        textB.setText(numB.toString())
//        users.setText(user.toString())
        var intent : Intent = getIntent()
        var bundle : Bundle? = intent.extras
        if (bundle != null){
            var numA : Int = bundle.getInt("A", 0)
            var numB : Int = bundle.getInt("B", 0)
            textA.setText(numA.toString())
            textB.setText(numB.toString())
            intent.putExtra("SUM", numA+numB)
        }
        btncaculator.setOnClickListener {
            setResult(SecondActivity.ResulutCode,intent)
            finish()
        }

    }
}