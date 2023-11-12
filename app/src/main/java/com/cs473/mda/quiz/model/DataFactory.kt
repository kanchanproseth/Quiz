package com.cs473.mda.quiz.model

class DataFactory {
    companion object {
        fun data(): ArrayList<Quiz> {
            val answerMap1 = HashMap<String, String>()
            answerMap1["a"] = "extends"
            answerMap1["b"] = "open"
            answerMap1["c"] = "Inherit"
            answerMap1["d"] = "close"
            val quiz1 = Quiz("If you want Inherit, in Kotlin you have declare the parent class with the keyword",
                answerMap1, "b")

            val answerMap = HashMap<String, String>()
            answerMap["a"] = "let myVariable = 10;"
            answerMap["b"] = "val myVariable: Int = 10"
            answerMap["c"] = "const myVariable = 10;"
            answerMap["d"] = "var myVariable: Int = 10"

            val quiz2 = Quiz("How do you declare a variable in Kotlin?",
                answerMap, "d")

            return arrayListOf(quiz1, quiz2)
        }
    }
}