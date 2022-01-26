package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7minuteworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFinishBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)


        setSupportActionBar(this.binding!!.actionBar)
        // show arrow left
        if(supportActionBar != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.binding!!.actionBar.setNavigationOnClickListener {
            this.onBackPressed()
        }


        binding!!.btnFinish.setOnClickListener {
            finish()
        }
    }
}