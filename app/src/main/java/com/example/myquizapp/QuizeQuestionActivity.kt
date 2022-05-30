package com.example.myquizapp

import android.content.Intent
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
    private var optionViews : ArrayList<TextView>? = null
    private var currentPositon : Int = 0
    private var questionSize : Int = 0
    private var selectedOptionIndex : Int = -1
    private var currectCount : Int = 0
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quize_question)

        userName = intent.getStringExtra(Constans.USER_NAME)

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

        optionViews = ArrayList<TextView>()
        optionViews?.add( 0, tvOption01!! )
        optionViews?.add(1, tvOption02!!)
        optionViews?.add(2, tvOption03!!)
        optionViews?.add(3, tvOption04!!)


        questions =  Constans.getQuestions()
        questions?.let {
            progressBar?.max = questions!!.size
            questionSize = questions!!.size
            setQuestion()
        }


        btnSubmit?.setOnClickListener() {
            if(btnSubmit?.text == "SUBMIT"){
                if( selectedOptionIndex == -1 ){
                    Toast.makeText(this,
                        "Please Selected Answer!", Toast.LENGTH_SHORT).show()
                }else{

                    val correctIndex : Int = getCorrectValueCurrentQuestion()

                    if( selectedOptionIndex == correctIndex ){
                        currectCount++
                        setCorrectOptionView( optionViews!![selectedOptionIndex] )
                    }
                    else{
                        setCorrectOptionView( optionViews!![correctIndex] )
                        setNotCorrectOptionView( optionViews!![selectedOptionIndex] )
                    }
                    if( currentPositon < questionSize - 1 )
                        btnSubmit?.text = "Go Next Question"
                    else
                        btnSubmit?.text = "Finish"

                }

            }else {
                if( currentPositon < questionSize - 1 ){
                    currentPositon++
                    setQuestion()
                    this.selectedOptionIndex = -1
                    setStyleOptionView()
                    btnSubmit?.text = "SUBMIT"
                }else{
                    val intent = Intent( this, ResultActivity::class.java)
                    intent.putExtra(Constans.USER_NAME, userName )
                    intent.putExtra(Constans.TOTAL_QUESTION_SIZE, questionSize )
                    intent.putExtra(Constans.CORRECT_ANSWER_SIZE, currectCount )
                    startActivity(intent)

                }
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

    private fun getCorrectValueCurrentQuestion() : Int {
        val question : Question = questions!![currentPositon]
        return  question.correctOptionIndex
    }

    private fun setSelectedOptionView(view : TextView, selectedOptionIndex : Int ){
        this.selectedOptionIndex = selectedOptionIndex
        setStyleOptionView()
    }

    private fun setStyleOptionView(){
        optionViews?.let {
            for( option in optionViews!! ) {
                if( this.selectedOptionIndex == -1){
                    option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
                    option.setTextColor( Color.BLACK )
                    option.typeface = Typeface.DEFAULT
                }else {
                    if( it.indexOf(option) != this.selectedOptionIndex ) {
                        option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
                        option.setTextColor( Color.BLACK )
                        option.typeface = Typeface.DEFAULT
                    }else {
                        option.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
                        option.setTextColor( Color.parseColor("#FF673AB7"))
                        option.typeface = Typeface.DEFAULT_BOLD
                    }


                }

            }
        }

    }

    private fun setCorrectOptionView( view: TextView ){
        view.background = ContextCompat.getDrawable(this,R.drawable.correct_option_border_bg)
        view.setTextColor( Color.WHITE )
        view.typeface = Typeface.DEFAULT_BOLD
    }

    private fun setNotCorrectOptionView( view: TextView ){
        view.background = ContextCompat.getDrawable(this,R.drawable.not_correct_option_border_bg )
        view.setTextColor( Color.WHITE )
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