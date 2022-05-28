package com.example.myquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class QuizeQuestionActivity() : AppCompatActivity() , View.OnClickListener {

    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var ivFlag : ImageView? = null
    private var tvOption01 : TextView? = null
    private var tvOption02 : TextView? = null
    private var tvOption03 : TextView? = null
    private var tvOption04 : TextView? = null
    private var tvQuestion : TextView? = null

    private var btnSubmit : Button? = null

    private var questions : ArrayList<Question>? = null
    private var currentPositon : Int = 0
    private var questionSize : Int = 0
    private var selectedOptionIndex : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quize_question)

        ivFlag = findViewById(R.id.ivFlag)
        progressBar = findViewById(R.id.progressQuestion)
        tvProgress = findViewById(R.id.tvProgressValue)
        tvOption01 = findViewById(R.id.tvOption01)
        tvOption01?.setOnClickListener(this)
        tvOption02 = findViewById(R.id.tvOption02)
        tvOption02?.setOnClickListener(this)
        tvOption03 = findViewById(R.id.tvOption03)
        tvOption03?.setOnClickListener(this)
        tvOption04 = findViewById(R.id.tvOption04)
        tvOption04?.setOnClickListener(this)
        tvQuestion = findViewById(R.id.tvQuestion )
        btnSubmit = findViewById(R.id.btnSubmit )


        questions =  Constans.getQuestions()
        questions?.let {
            progressBar?.max = questions!!.size
            questionSize = questions!!.size
            setQuestion()
        }


        btnSubmit?.setOnClickListener() {
            if( selectedOptionIndex == -1 ){
                Toast.makeText(this,
                    "Please Selected Answer!", Toast.LENGTH_LONG).show()
            }

            if( currentPositon < questionSize - 1 ){
                currentPositon++
                setQuestion()
            }

        }
    }


    fun setQuestion() {
        val question : Question = questions!![currentPositon]
        tvQuestion?.text = question.question
        ivFlag?.setImageResource( question.Image)
        progressBar?.progress = currentPositon+1
        tvProgress?.text = "${currentPositon+1}/${questions!!.size}"
        tvOption01?.text = question.optionOne
        tvOption02?.text = question.optionTwo
        tvOption03?.text = question.optionThree
        tvOption04?.text = question.optionFour
    }

    fun setSelectedOptionView( view : TextView, selectedOptionIndex : Int ){
        this.selectedOptionIndex = selectedOptionIndex
        view.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
        view.setTextColor( Color.parseColor("#FF673AB7"))
        view.typeface = Typeface.DEFAULT_BOLD



    }

    //View.OnClickListener
    override fun onClick(view: View?) {
        if( view?.id == R.id.tvOption01 )
            setSelectedOptionView(view as TextView, 0 )
        else if( view?.id == R.id.tvOption02 )
            setSelectedOptionView( view as TextView, 1 )
        else if( view?.id == R.id.tvOption03 )
            setSelectedOptionView( view as TextView, 2 )
        else if( view?.id == R.id.tvOption04 )
            setSelectedOptionView( view as TextView, 3 )
    }


}