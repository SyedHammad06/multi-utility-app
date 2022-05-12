package com.example.multi_utilityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculatorMenu = findViewById<ImageButton>(R.id.calculator_menu)
        val quizMenu = findViewById<ImageButton>(R.id.quiz_menu)
        val splitBillsMenu = findViewById<ImageButton>(R.id.split_bill_menu)

        calculatorMenu.setOnClickListener{
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        quizMenu.setOnClickListener{
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        splitBillsMenu.setOnClickListener{
            val intent = Intent(this, SplitBillActivity::class.java)
            startActivity(intent)
        }
    }
}