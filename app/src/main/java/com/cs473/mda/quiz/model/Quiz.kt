package com.cs473.mda.quiz.model

import java.io.Serializable

data class Quiz(val question: String, val answer: Map<String, String>, val correctAnswerKey: String): Serializable
