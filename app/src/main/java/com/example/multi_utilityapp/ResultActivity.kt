package com.example.multi_utilityapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getStringExtra(Constants.result)
        val totalQuestions = intent.getStringExtra(Constants.totalQuestions)

        val resultView = findViewById<TextView>(R.id.score)
        val btnFinish = findViewById<Button>(R.id.finish)

        resultView.text = "Your score is ${result} out of ${totalQuestions}"

        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}