package com.example.a7minuteworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.NumberCurrentExerciseBinding

class ExerciseViewAdapter(val exerciseList: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseViewAdapter.ViewHolder>(){

    class ViewHolder(View: NumberCurrentExerciseBinding):RecyclerView.ViewHolder(View.root){
        val textView = View.tvNumberExercise
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NumberCurrentExerciseBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = exerciseList[position].getID().toString()
        when{
            exerciseList[position].getIsSelected() -> holder.textView.setBackgroundResource(R.drawable.circle_green_border)
            exerciseList[position].getIsFinish() -> holder.textView.setBackgroundResource(R.drawable.circle_green_solid)
            else -> holder.textView.setBackgroundResource(R.drawable.circle_grey_solid)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}