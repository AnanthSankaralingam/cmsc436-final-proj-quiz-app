package com.example.cmsc_infinity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    private lateinit var resultsTV : TextView

    private lateinit var homeButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)

        val score = intent.getIntExtra("quizScore", 0)
        val size = intent.getIntExtra("questionSetResponseSize", 0)

        resultsTV = findViewById<TextView>(R.id.resultsTV)
        resultsTV.text = "Results: ${score}/${size}"

        homeButton = findViewById<Button>(R.id.returnHomeButton)

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lastScore", score)
            startActivity(intent)
        }

    }
}