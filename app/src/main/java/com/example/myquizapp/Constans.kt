package com.example.myquizapp

object Constans {

    const val USER_NAME : String ="user_name"
    const val TOTAL_QUESTION_SIZE : String = "total_question_size"
    const val CORRECT_ANSWER_SIZE : String = "correct_answer_size"

    fun getQuestions() : ArrayList<Question>{
        val questionString = "What country does this flag belong to?"
        var questions = ArrayList<Question>()
        var qur01 = Question (
            1,
            questionString,
            R.drawable.ic_flag_of_argentina,
            "America",
            "South Korea",
            "Japan",
            "Argaentina",
            3 )

        questions.add(qur01)

        var qur02 = Question (
            2,
            questionString,
            R.drawable.ic_flag_of_australia,
            "South Korea",
            "Australia",
            "French",
            "America",
            1 )

        questions.add(qur02)

        var qur03 = Question (
            3,
            questionString,
            R.drawable.ic_flag_of_belgium,
            "French",
            "Australia",
            "Belgium",
            "America",
            2 )

        questions.add(qur03)

        var qur04 = Question (
            4,
            questionString,
            R.drawable.ic_flag_of_denmark,
            "French",
            "Australia",
            "Belgium",
            "Denmark",
            3 )

        questions.add(qur04)



        return questions

    }
}