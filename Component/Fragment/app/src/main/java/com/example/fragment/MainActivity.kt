package com.example.fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var manager : FragmentManager = supportFragmentManager
//        var transition : FragmentTransaction = manager.beginTransaction()
//        var blackFragment = BlankFragment()
//        transition.add(R.id.root, blackFragment)
//        transition.addToBackStack(null)
//        transition.commit()

        add.setOnClickListener {
            var manager : FragmentManager = supportFragmentManager
            var transaction : FragmentTransaction = manager.beginTransaction()
            //var blacFragment = BlankFragment()
            transaction.add(R.id.root,  BlankFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        replace.setOnClickListener {
            var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.root, BlankFragment2())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        pop.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
        
    }
}