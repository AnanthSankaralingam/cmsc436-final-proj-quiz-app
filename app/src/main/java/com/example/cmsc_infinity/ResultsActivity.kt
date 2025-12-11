package com.example.cmsc_infinity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class ResultsActivity : AppCompatActivity() {

    private lateinit var resultsTV : TextView
    private lateinit var homeButton : Button
    private lateinit var emailButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)

        val score = intent.getIntExtra("quizScore", 0)
        val size = intent.getIntExtra("questionSetResponseSize", 0)

        val db = FirebaseDatabase.getInstance().getReference("leaderboard")

        val entry = mapOf(
            "score" to score,
            "course" to intent.getStringExtra("courseSelected"),
            "timestamp" to System.currentTimeMillis()
        )

        db.push().setValue(entry)



        resultsTV = findViewById<TextView>(R.id.resultsTV)
        resultsTV.text = "Results: ${score}/${size}"

        homeButton = findViewById<Button>(R.id.returnHomeButton)
        emailButton = findViewById(R.id.sendEmailButton)

        homeButton.setOnClickListener {
            val prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE)
            prefs.edit().putInt("LAST_SCORE", score).apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        emailButton.setOnClickListener {
            val subject = "Your Quiz Results"
            val body = "You scored $score out of $size on the CMSC Infinity Quiz!"

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = android.net.Uri.parse("mailto:")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            startActivity(Intent.createChooser(emailIntent, "Send using Gmail"))
        }



    }
}