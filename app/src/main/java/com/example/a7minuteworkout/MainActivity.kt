package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var biding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding!!.root)


        biding!!.frameClick.setOnClickListener {
            startActivity(Intent(this,ExerciseActivity::class.java))
        }


        biding!!.flBMI.setOnClickListener {
            startActivity(Intent(this,bmiActivity::class.java))
        }


        biding!!.flHistory.setOnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        }
    }
}