package com.example.myquizapp

data class Question(
    val id : Int,
    val question : String,
    val Image : Int,
    var optionOne : String,
    var optionTwo : String,
    var optionThree : String,
    var optionFour : String,
    var correctOptionIndex : Int
)

