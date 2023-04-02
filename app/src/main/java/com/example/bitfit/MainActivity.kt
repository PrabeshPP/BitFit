package com.example.bitfit

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var items:MutableList<Food>
    private  lateinit var itemsRv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRv=findViewById(R.id.recyclerView)
        items=ArrayList<Food>()
        val adapter=FoodAdapter(items)
        itemsRv.adapter=adapter
        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect{
                databaseList->databaseList.map {entity->
                    Food(
                    entity.foodName, entity.foodCalories
                    )
            }.map {mappedList->
                (items as ArrayList<Food>).addAll(listOf(mappedList))
                adapter.notifyDataSetChanged()

            }
            }
        }
        itemsRv.layoutManager=LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            itemsRv.addItemDecoration(dividerItemDecoration)
        }

        val button=findViewById<Button>(R.id.button2);
        button.setOnClickListener {
            val intent=Intent(this,AddActivity::class.java)
            this.startActivity(intent)
        }

    }
}