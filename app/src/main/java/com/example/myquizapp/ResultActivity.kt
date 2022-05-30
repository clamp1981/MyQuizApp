package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private var userName : String? = null
    private var totalCount : Int = 0
    private var correctCount : Int = 0

    private var tvUserName : TextView? = null
    private var tvScore : TextView? = null
    private var btnFinish : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        userName = intent.getStringExtra(Constans.USER_NAME)
        totalCount = intent.getIntExtra(Constans.TOTAL_QUESTION_SIZE, 0 )
        correctCount = intent.getIntExtra(Constans.CORRECT_ANSWER_SIZE, 0 )

        tvUserName = findViewById(R.id.tvUserName)
        tvScore = findViewById(R.id.tvScore)
        btnFinish = findViewById(R.id.btnFinish)
        btnFinish?.setOnClickListener {

            startActivity(Intent( this, MainActivity::class.java))
        }

        tvUserName?.text = userName
        tvScore?.text = "Your score is $correctCount out of $totalCount"

    }
}