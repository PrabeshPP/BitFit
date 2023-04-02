package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val addButton=findViewById<Button>(R.id.button)
        val editFoodName=findViewById<EditText>(R.id.editTextTextPersonName)
        val editFoodCalories=findViewById<EditText>(R.id.editTextTextPersonName2)

        addButton.setOnClickListener{
            var currentFoodName=editFoodName.getText().toString()
            var currentCalories=editFoodCalories.getText().toString()
            var food=Food(currentFoodName,currentCalories)
            lifecycleScope.launch(IO){
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(foodName = food.foodName,
                    foodCalories = food.calories))
            }
            val intent=Intent(this,MainActivity::class.java)
            this.startActivity(intent)
        }

    }
}