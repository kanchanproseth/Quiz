package com.cs473.mda.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cs473.mda.quiz.R
import com.cs473.mda.quiz.model.DataFactory
import com.cs473.mda.quiz.model.Quiz
import com.cs473.mda.quiz.model.QuizDelegate

class MainActivity : AppCompatActivity(), QuizDelegate {
    private var score: Int = 0
    private var currentQuestionIndex = 0;
    private var data = DataFactory.data()

    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragmentQuiz(data[currentQuestionIndex], false)
    }

    private fun createFragmentQuiz(quiz: Quiz, isLast: Boolean) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = QuizFragment.newInstance(quiz, isLast)
        fragmentTransaction.add(R.id.body_view, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun replaceFragmentQuiz(quiz: Quiz, isLast: Boolean) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = QuizFragment.newInstance(quiz, isLast)
        fragmentTransaction.replace(R.id.body_view, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun submit(isCorrect: Boolean) {
        score = if (isCorrect)  score + 50 else score+ 0
        currentQuestionIndex += 1
        if (currentQuestionIndex > 1) {
            val alert = SuccessDialogMessage.newInstance(score)
            alert.show(supportFragmentManager, "Result")
        } else {
            replaceFragmentQuiz(data[currentQuestionIndex], true)
        }

    }

    override fun reset() {
        score = 0
        currentQuestionIndex = 0
        replaceFragmentQuiz(data[0], false)
    }

}