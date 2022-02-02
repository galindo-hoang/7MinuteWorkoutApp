package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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

        val historyDao = (application as HistoryApp).db.historyDao()
        addHistory(historyDao)
    }

    private fun addHistory(historyDao: HistoryDao) {
        val currentDate = SimpleDateFormat("dd MMM yyyy - HH:mm:ss a").format(Calendar.getInstance().time)
        lifecycleScope.launch {
            historyDao.insertHistory(HistoryEntity(Date = currentDate))
        }

    }
}