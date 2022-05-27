package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.TextView

class QuizeQuestionActivity() : AppCompatActivity() {
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quize_question)

        val tvQuestion : TextView = findViewById(R.id.tvQuestion )
        val questions : ArrayList<Question> =  Constans.getQuestions();
        Log.i( "Question Size is ", "${questions.size}")



    }



}