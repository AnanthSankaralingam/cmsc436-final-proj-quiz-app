package com.example.cmsc_infinity

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import org.json.JSONObject

class QuestionSet {
    var course: String = ""
    var questions: ArrayList<Question> = ArrayList()

    // Firebase reference
    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()

    // fetch data from Firebase
    fun loadQuestionsFromFirebase(courseName: String, onComplete: (Boolean) -> Unit) {
        this.course = courseName
        val ref: DatabaseReference = db.getReference(courseName) // question data by course

        // static data - don't need constant listener
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    if (!snapshot.exists()) {
                        Log.w("QuestionSet", "No data found for $courseName")
                        onComplete(false)
                        return
                    }

                    questions.clear()

                    // parse response of nested questions as json
                    for (questionSnap in snapshot.children) {
                        val json = JSONObject()
                        json.put("question", questionSnap.child("question").value.toString())

                        // Build the answer options object
                        val optionsJson = JSONObject()
                        val optionsSnap = questionSnap.child("options")
                        for (optKey in optionsSnap.children) {
                            optionsJson.put(optKey.key.toString(), optKey.value.toString())
                        }
                        json.put("options", optionsJson)

                        // get the right answer
                        json.put("correct", questionSnap.child("correct").value.toString())

                        questions.add(Question(json))
                    }

                    Log.d("QuestionSet", "Loaded ${questions.size} questions for $courseName")
                    onComplete(true)

                } catch (e: Exception) {
                    Log.e("QuestionSet", "Error parsing questions: ${e.message}")
                    onComplete(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("QuestionSet", "Firebase error: ${error.message}")
                onComplete(false)
            }
        })
    }

}