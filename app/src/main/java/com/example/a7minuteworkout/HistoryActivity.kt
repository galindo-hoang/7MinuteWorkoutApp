package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding:ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(this.binding!!.actionBar)
        // show arrow left
        if(supportActionBar != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "History"
        this.binding!!.actionBar.setNavigationOnClickListener {
            onBackPressed()
        }

        val HistoryDao = (application as HistoryApp).db.historyDao()
        showHistory(HistoryDao)
    }

    private fun showHistory(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetchAllHistory().collect {
                if(it.isNotEmpty()){
                    binding?.rcvHistoryList?.visibility = View.VISIBLE
                    binding?.rcvHistoryList?.adapter = HistoryAdapter(it)
                    binding?.rcvHistoryList?.layoutManager = LinearLayoutManager(this@HistoryActivity)
                }else{
                    binding?.rcvHistoryList?.visibility = View.INVISIBLE
                }
            }
        }
    }


}