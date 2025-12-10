package com.example.cmsc_infinity

import org.json.JSONObject

// model of Q&A data
class Question {
    var question: String
    var options: Map<String, String>
    var correct: String

    // constructor accepts QuestionSet data from firebase
    constructor(obj: JSONObject) {
        this.question = obj.getString("question")
        val opts = obj.getJSONObject("options")
        this.options = mapOf(
            "A" to opts.getString("A"),
            "B" to opts.getString("B"),
            "C" to opts.getString("C"),
            "D" to opts.getString("D")
        )
        this.correct = obj.getString("correct")
    }
}