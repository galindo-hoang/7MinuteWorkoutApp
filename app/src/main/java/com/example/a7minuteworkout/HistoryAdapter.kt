package com.example.a7minuteworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ItemHistoryBinding

class HistoryAdapter(val list: List<HistoryEntity>):RecyclerView.Adapter<HistoryAdapter.HistoryViewHoler>() {
    class HistoryViewHoler(itemView: ItemHistoryBinding):RecyclerView.ViewHolder(itemView.root){
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHoler {
        return HistoryViewHoler(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryViewHoler, position: Int) {
        holder.binding.tvDate.text = list[position].Date
        holder.binding.tvOrder.text = list[position].Id.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}