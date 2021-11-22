package com.example.explicit
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        public val Request : Int = 12
        private lateinit var sum : TextView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnStart : Button = findViewById(R.id.startActivity)
        var textA : TextView = findViewById(R.id.nhapA)
        var textB : TextView = findViewById(R.id.nhapB)
         sum  = findViewById(R.id.textsum)
//        btnStart.setOnClickListener {
//            var intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("A", 3)
//            intent.putExtra("B", 8)
//            intent.putExtra("User", User("Thong", 21))
//            startActivity(intent)
//        }

        btnStart.setOnClickListener {
            var intent : Intent = Intent(this, SecondActivity::class.java)
            var bundle : Bundle = Bundle()
            bundle.putInt("A", Integer.parseInt(textA.text.toString()))
            bundle.putInt("B", Integer.parseInt(textB.text.toString()))
            intent.putExtras(bundle)
            startActivityForResult(intent, MainActivity.Request)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainActivity.Request && resultCode == SecondActivity.ResulutCode)
            if (data != null){
                var Sum = data.getIntExtra("SUM", 0)
                sum.setText(Sum.toString())
            }
    }
}