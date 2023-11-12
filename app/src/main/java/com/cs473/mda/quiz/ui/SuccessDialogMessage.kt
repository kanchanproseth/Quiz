package com.cs473.mda.quiz.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Date

class SuccessDialogMessage: DialogFragment() {
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            score = it.getInt("score")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        return activity?.let {
            // Use the Builder class for convenient dialog construction.
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Congratulation! You submitted on $currentDate, \n you achieved $score%")
                .setPositiveButton("Okay") { dialog, id ->

                }
            // Create the AlertDialog object and return it.
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        @JvmStatic
        fun newInstance(score: Int) =
            SuccessDialogMessage().apply {
                arguments = Bundle().apply {
                    putInt("score", score)
                }
            }
    }
}