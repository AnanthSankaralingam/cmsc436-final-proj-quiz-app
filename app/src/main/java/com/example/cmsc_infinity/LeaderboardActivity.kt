package com.example.cmsc_infinity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardText: TextView
    private lateinit var backHomeButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        leaderboardText = findViewById(R.id.leaderboardText)
        backHomeButton = findViewById(R.id.backHomeButton)

        backHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val db = FirebaseDatabase.getInstance().getReference("leaderboard")

        db.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = ArrayList<LeaderboardEntry>()

                for (child in snapshot.children) {
                    val score = child.child("score").getValue(Int::class.java) ?: 0
                    val course = child.child("course").getValue(String::class.java) ?: "Unknown"
                    val timestamp = child.child("timestamp").getValue(Long::class.java) ?: 0

                    list.add(LeaderboardEntry(score, course, timestamp))
                }

                if (list.isEmpty()) {
                    leaderboardText.text = "No scores yet — start playing!"
                    return
                }

                list.sortByDescending { it.score }
                val topThree = list.take(3)

                val sb = StringBuilder("Top 3 Scores:\n\n")
                for ((i, entry) in topThree.withIndex()) {
                    sb.append("${i + 1}. ${entry.score} points — ${entry.course}\n")
                }

                leaderboardText.text = sb.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                leaderboardText.text = "Error loading leaderboard."
            }
        })
    }
}
