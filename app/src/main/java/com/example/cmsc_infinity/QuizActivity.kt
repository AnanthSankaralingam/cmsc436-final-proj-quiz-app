package com.example.cmsc_infinity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// controller for the quiz question fetching and display
class QuizActivity : AppCompatActivity() {
    private lateinit var questionView : TextView
    private lateinit var answerOptionAButton : Button
    private lateinit var answerOptionBButton : Button
    private lateinit var answerOptionCButton : Button
    private lateinit var answerOptionDButton : Button

    private lateinit var progressBar : ProgressBar

    private var questionSetResponse: ArrayList<Question> = ArrayList()
    private var currQuestion = 0
    private var score = 0
    private var courseName: String = "CMSC131"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionView = findViewById(R.id.questionText)
        answerOptionAButton = findViewById(R.id.answerOptionA)
        answerOptionBButton = findViewById(R.id.answerOptionB)
        answerOptionCButton = findViewById(R.id.answerOptionC)
        answerOptionDButton = findViewById(R.id.answerOptionD)
        progressBar = findViewById(R.id.progressBar)

        // retrieve which course button was clicked via intent
        courseName = intent.getStringExtra("courseSelected") ?: "CMSC131"

        // fetch course model with questions
        val questionSet = QuestionSet()
        questionSet.loadQuestionsFromFirebase(courseName) { success ->
            if (success) {
                questionSetResponse = questionSet.questions
                Log.w("QuizActivity", "Loaded ${questionSet.questions.size} questions")
                showQuestion(currQuestion)
            } else {
                Log.w("QuizActivity", "Loaded ${questionSet.questions.size} questions")
            }
        }

    }

    private fun showQuestion(index: Int) {
        if (index >= questionSetResponse.size) {
            //questionView.text = "quiz Score: $score/${questionSetResponse.size}"
            disableButtons()

            // go to results
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("quizScore", score)
            intent.putExtra("questionSetResponseSize", questionSetResponse.size)
            intent.putExtra("courseSelected", courseName)
            startActivity(intent)
            return
        }


        val q = questionSetResponse[index]
        questionView.text = q.question
        answerOptionAButton.text = q.options["A"]
        answerOptionBButton.text = q.options["B"]
        answerOptionCButton.text = q.options["C"]
        answerOptionDButton.text = q.options["D"]

        // assign button listeners
        val onClick = { choice: String ->
            if (choice == q.correct) score++
            currQuestion++
            showQuestion(currQuestion)
            progressBar.progress += 10
        }

        answerOptionAButton.setOnClickListener { onClick("A") }
        answerOptionBButton.setOnClickListener { onClick("B") }
        answerOptionCButton.setOnClickListener { onClick("C") }
        answerOptionDButton.setOnClickListener { onClick("D") }

    }

    private fun disableButtons() {
        answerOptionAButton.isEnabled = false
        answerOptionBButton.isEnabled = false
        answerOptionCButton.isEnabled = false
        answerOptionDButton.isEnabled = false
    }


}