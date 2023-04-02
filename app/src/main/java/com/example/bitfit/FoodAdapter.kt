package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val items:List<Food>): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val foodTextView:TextView
        val caloriesTextView:TextView


        init {
            foodTextView=itemView.findViewById(R.id.textViewFood)
            caloriesTextView=itemView.findViewById(R.id.textViewCalories)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
        val inflater=LayoutInflater.from(context)
        val contactView=inflater.inflate(R.layout.food_item,parent,false)
        return ViewHolder(contactView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val item= items[position]
        holder.foodTextView.text=item.foodName
        holder.caloriesTextView.text=item.calories

    }

    override fun getItemCount(): Int {
        return items.size
    }
}