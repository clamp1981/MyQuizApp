package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart : Button = findViewById(R.id.btnStart)
        val txtName : EditText = findViewById(R.id.txtName )

        btnStart.setOnClickListener{
            if( !txtName?.text.isNotEmpty() ) {
                Toast.makeText(this,
                    "Please Enter your name!", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent( this, QuizeQuestionActivity::class.java)
                startActivity(intent)
                finish()

            }


        }
    }
}