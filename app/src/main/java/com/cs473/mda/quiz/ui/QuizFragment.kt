package com.cs473.mda.quiz.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.cs473.mda.quiz.R
import com.cs473.mda.quiz.model.Quiz
import com.cs473.mda.quiz.model.QuizDelegate

class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var quiz: Quiz? = null
    private var isLast = false

    private var questionTextView: TextView? = null

    private var firstRadioButton: RadioButton? = null
    private var firstAnswerTextView: TextView? = null

    private var secondRadioButton: RadioButton? = null
    private var secondAnswerTextView: TextView? = null

    private var thirdRadioButton: RadioButton? = null
    private var thirdAnswerTextView: TextView? = null

    private var fourthRadioButton: RadioButton? = null
    private var fourthAnswerTextView: TextView? = null

    private var resetButton: Button? = null
    private var submitButton: Button? = null

    private var selectedAnswerKey: String? = null

    lateinit var quizDelegate: QuizDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        quizDelegate = context as QuizDelegate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quiz = it.getSerializable("quiz") as Quiz?
            isLast = it.getBoolean("isLast")
        }
    }

    private fun setupView() {
        questionTextView = view?.findViewById(R.id.quiz_text_view)

        firstRadioButton = view?.findViewById(R.id.first_answer_radio_btn)
        firstAnswerTextView = view?.findViewById(R.id.first_answer_text_view)

        secondRadioButton = view?.findViewById(R.id.second_answer_radio_btn)
        secondAnswerTextView = view?.findViewById(R.id.second_answer_text_view)

        thirdRadioButton = view?.findViewById(R.id.third_answer_radio_btn)
        thirdAnswerTextView = view?.findViewById(R.id.third_answer_text_view)

        fourthRadioButton = view?.findViewById(R.id.fourth_answer_radio_btn)
        fourthAnswerTextView = view?.findViewById(R.id.fourth_answer_text_view)

        resetButton = view?.findViewById(R.id.reset_button)
        submitButton = view?.findViewById(R.id.submit_button)

        if (!isLast) {
            submitButton?.text = "Next"
        }
    }

    private fun bindSelectedRadioButton() {
        firstRadioButton?.setOnClickListener {
            selectedAnswerKey = "a"
            firstRadioButton?.isChecked = true
            secondRadioButton?.isChecked = false
            thirdRadioButton?.isChecked = false
            fourthRadioButton?.isChecked = false
        }

        secondRadioButton?.setOnClickListener {
            selectedAnswerKey = "b"
            firstRadioButton?.isChecked = false
            secondRadioButton?.isChecked = true
            thirdRadioButton?.isChecked = false
            fourthRadioButton?.isChecked = false
        }

        thirdRadioButton?.setOnClickListener {
            selectedAnswerKey = "c"
            firstRadioButton?.isChecked = false
            secondRadioButton?.isChecked = false
            thirdRadioButton?.isChecked = true
            fourthRadioButton?.isChecked = false
        }

        fourthRadioButton?.setOnClickListener {
            selectedAnswerKey = "d"
            firstRadioButton?.isChecked = false
            secondRadioButton?.isChecked = false
            thirdRadioButton?.isChecked = false
            fourthRadioButton?.isChecked = true
        }

        resetButton?.setOnClickListener {
            quizDelegate.reset()
        }

        submitButton?.setOnClickListener {
            if (selectedAnswerKey.isNullOrBlank()) {
                Toast.makeText(this.context?.applicationContext, "Please select Answer", Toast.LENGTH_SHORT).show()
            } else {
                quizDelegate.submit((selectedAnswerKey!! == quiz?.correctAnswerKey))
            }

        }
    }

    private fun resetAll() {
        firstRadioButton?.isChecked = false
        secondRadioButton?.isChecked = false
        thirdRadioButton?.isChecked = false
        fourthRadioButton?.isChecked = false
    }

    private fun setupData(quiz: Quiz) {
        questionTextView?.text = quiz.question

        firstAnswerTextView?.text = "a) ${quiz.answer["a"]}"

        secondAnswerTextView?.text = "b) ${quiz.answer["b"]}"

        thirdAnswerTextView?.text = "c) ${quiz.answer["c"]}"

        fourthAnswerTextView?.text = "d) ${quiz.answer["d"]}"

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupData(quiz!!)
        resetAll()
        bindSelectedRadioButton()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(quiz: Quiz, isLast: Boolean) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("quiz", quiz)
                    putBoolean("isLast", isLast)
                }
            }
    }
}