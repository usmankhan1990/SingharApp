package com.singhar.ui

import android.app.AlertDialog
import android.content.Context

class CustomDialog(context: Context) : AlertDialog.Builder(context) {

    lateinit var onResponse: (r : ResponseType) -> Unit

    enum class ResponseType {
        YES, NO
    }

    fun show(title: String,
             message: String,
             drawable: Int = android.R.drawable.ic_dialog_email,
             singleButton: Boolean = false,
             yesButton: String = "Yes",
             noButton: String = "No",
             listener: (r : ResponseType) -> Unit) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(drawable)
        onResponse = listener


        // performing positive action
        builder.setPositiveButton(yesButton) { _, _ ->
            onResponse(ResponseType.YES)
        }

        if(!singleButton){
            // performing negative action
            builder.setNegativeButton(noButton) { _, _ ->
                onResponse(ResponseType.NO)
            }
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()

        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}