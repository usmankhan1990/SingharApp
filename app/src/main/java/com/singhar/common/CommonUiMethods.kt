package com.singhar.common

import android.content.Context
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.singhar.R

fun customDialog(
    context: Context,
    title: String = "",
    description: String = "",
    drawable: Int = R.drawable.alert,
    layoutInflater: LayoutInflater
) {
    val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
    val customDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .show()
    val btDismiss = dialogView.findViewById<Button>(R.id.btnOk)
    val txtTitle = dialogView.findViewById<TextView>(R.id.txtTitle)
    val txtDescription = dialogView.findViewById<TextView>(R.id.txtDescription)
    val imgTop = dialogView.findViewById<ImageView>(R.id.imgTop)

    txtTitle.text = title
    txtDescription.text = description

    imgTop.setBackgroundResource(drawable)

    btDismiss.setOnClickListener {
        customDialog.dismiss()
    }

    customDialog.setCancelable(false)
}

fun emailValidator(editText: EditText, context: Context): Boolean {

    val emailToText = editText.text.toString()
    return if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
        true
//        Toast.makeText(context, "Email Verified !", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Kindly put valid Email Address", Toast.LENGTH_SHORT).show()
        false
    }
}

fun phoneNumberValidator(editText: EditText, context: Context): Boolean {

    val emailToText = editText.text.toString()
    return if (emailToText.isNotEmpty() && emailToText.length >= 11) {
        true
    } else {
        Toast.makeText(context, "Kindly put valid contact number", Toast.LENGTH_SHORT).show()
        false
    }
}