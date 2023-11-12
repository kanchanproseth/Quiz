package com.cs473.mda.quiz.model

interface QuizDelegate {
    fun submit(isCorrect: Boolean)
    fun reset()
}