package com.example.multi_utilityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SplitBillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split_bill)

        val amountField = findViewById<EditText>(R.id.amount_field)
        val noOfPeople = findViewById<EditText>(R.id.number_people)
        val submitBtn = findViewById<Button>(R.id.submit)
        val amountTitle = findViewById<TextView>(R.id.amount_title)
        val amount = findViewById<TextView>(R.id.amount)

        submitBtn.setOnClickListener{
            val operand1 = Integer.parseInt(amountField.text.toString())
            val operand2 = Integer.parseInt(noOfPeople.text.toString())
            amountTitle.text = "Amount each person has to pay :-"
            amount.text = "${operand1/operand2}"
        }
    }
}