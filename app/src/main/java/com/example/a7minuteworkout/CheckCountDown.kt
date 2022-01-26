package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CheckCountDown : AppCompatActivity() {
    var start:Long = 30
    var a: CountDownTimer? = null
    var remaning = start

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_count_down)



        findViewById<Button>(R.id.btnCheckStart).setOnClickListener {
            if(a == null){
                a = object: CountDownTimer(remaning*1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        --remaning
                        findViewById<TextView>(R.id.tvCheck).setText("seconds remaining: " + millisUntilFinished / 1000)
                    }

                    override fun onFinish() {
                        findViewById<TextView>(R.id.tvCheck).setText("done!")
                    }
                }.start()
            }
        }


        findViewById<Button>(R.id.btnCheckPause).setOnClickListener {
            if(a != null){
                a?.cancel()
                a = null
            }
        }


        findViewById<Button>(R.id.btnCheckRestart).setOnClickListener {
            if(a != null){
                remaning = start
                findViewById<TextView>(R.id.tvCheck).setText("seconds remaining: " + remaning)
                a?.cancel()
                a=null
            }
        }
    }
}