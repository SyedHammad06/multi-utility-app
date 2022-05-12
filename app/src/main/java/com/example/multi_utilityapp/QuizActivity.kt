package com.example.multi_utilityapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private var currentPosition: Int = 1
    private var questionsList: ArrayList<Question>? = null
    private var selectedOption: Int = 0

    private var questionView: TextView? = null
    private var imageView: ImageView? = null
    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null
    private var progressBar: ProgressBar? = null
    private var progressText: TextView? = null
    private var submitBtn: Button? = null
    private var score: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionView = findViewById(R.id.question)
        imageView = findViewById(R.id.image)
        optionOne = findViewById(R.id.option_one)
        optionTwo = findViewById(R.id.option_two)
        optionThree = findViewById(R.id.option_three)
        optionFour = findViewById(R.id.option_four)
        progressBar = findViewById(R.id.progress)
        progressText = findViewById(R.id.progress_text)
        submitBtn = findViewById(R.id.submit)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        questionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {
        defaultOptions()
        val currentQuestion: Question = questionsList!![currentPosition - 1]

        progressBar?.progress = currentPosition
        progressText?.text = "${currentPosition} / ${questionsList?.size}"

        questionView?.text = currentQuestion.question
        imageView?.setImageResource(currentQuestion.image)
        optionOne?.text = currentQuestion.optionOne
        optionTwo?.text = currentQuestion.optionTwo
        optionThree?.text = currentQuestion.optionThree
        optionFour?.text = currentQuestion.optionFour
        submitBtn?.text = "Submit"
    }

    private fun defaultOptions() {
        val options = ArrayList<TextView>()

        optionOne?.let {
            options.add(0, it)
        }
        optionTwo?.let{
            options.add(1, it)
        }
        optionThree?.let{
            options.add(2, it)
        }
        optionFour?.let{
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option)
        }
    }

    private fun selectOption(v: TextView, selected: Int) {
        defaultOptions()

        selectedOption = selected

        v.setTextColor(Color.parseColor("#363A43"))
        v.typeface = Typeface.DEFAULT_BOLD
        v.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
    }

    private fun selectAnswer(answer: Int, drawable: Int) {
        when (answer) {
            1 -> optionOne?.background = ContextCompat.getDrawable(this, drawable)
            2 -> optionTwo?.background = ContextCompat.getDrawable(this, drawable)
            3 -> optionThree?.background = ContextCompat.getDrawable(this, drawable)
            4 -> optionFour?.background = ContextCompat.getDrawable(this, drawable)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.option_one -> {
                optionOne?.let {
                    selectOption(it, 1)
                }
            }
            R.id.option_two -> {
                optionTwo?.let {
                    selectOption(it, 2)
                }
            }
            R.id.option_three -> {
                optionThree?.let {
                    selectOption(it, 3)
                }
            }
            R.id.option_four -> {
                optionFour?.let {
                    selectOption(it, 4)
                }
            }
            R.id.submit -> {
                if (selectedOption == 0) {
                    currentPosition++
                    when {
                        currentPosition <= questionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.result, score.toString())
                            intent.putExtra(Constants.totalQuestions, questionsList!!.size.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = questionsList?.get(currentPosition - 1)

                    if (selectedOption != question!!.correctOption) {
                        score--;
                        selectAnswer(selectedOption, R.drawable.wrong_answer)
                    }
                    selectAnswer(question.correctOption, R.drawable.correct_answer)

                    if (currentPosition == questionsList!!.size) {
                        submitBtn?.text = "Finish"
                    } else {
                        submitBtn?.text = "Go to the next question"
                    }
                    selectedOption = 0
                }
            }
        }
    }
}