package com.example.multi_utilityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.lang.ArithmeticException

class CalculatorActivity : AppCompatActivity() {
    var output: TextView? = null
    var previousNumber: Boolean = false
    var onlyOneOperator: Boolean = false
    var onlyOneDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        output = findViewById(R.id.main_display)
    }

    fun submit(v: View) {
        previousNumber = true
        output?.append((v as Button).text)
    }

    fun operator(v: View) {
        if (previousNumber and !onlyOneOperator) {
            previousNumber = false
            onlyOneOperator = true
            onlyOneDecimal = false
            output?.append((v as Button).text)
        }
    }

    fun clear(v: View) {
        output?.text = ""
        previousNumber = false
        onlyOneOperator = false
        onlyOneDecimal = false
    }

    fun decimalPoint(v: View) {
        if (previousNumber && !onlyOneDecimal) {
            previousNumber = false
            onlyOneDecimal = true
            output?.append((v as Button).text)
        }
    }

    fun equal(v: View) {
        if (previousNumber) {
            var tvVal = output?.text.toString()

            try {
                if (tvVal.startsWith("-")) {
                    tvVal = tvVal.substring(1)
                }

                if (tvVal.contains("-")) {
                    val operands = tvVal.split("-")

                    var a = operands[0].toDouble()
                    val b = operands[1].toDouble()

                    output?.text = (a - b).toString()
                } else if (tvVal.contains("+")) {
                    val operands = tvVal.split("+")

                    var a = operands[0].toDouble()
                    val b = operands[1].toDouble()

                    output?.text = (a + b).toString()
                } else if (tvVal.contains("*")) {
                    val operands = tvVal.split("*")

                    var a = operands[0].toDouble()
                    val b = operands[1].toDouble()

                    output?.text = (a * b).toString()
                } else {
                    val operands = tvVal.split("/")

                    var a = operands[0].toDouble()
                    val b = operands[1].toDouble()

                    output?.text = (a / b).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
}