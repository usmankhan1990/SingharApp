package com.singhar.common

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.parse.ParseException
import com.parse.ParseUser


fun login(user: String, pass: String, context: Context, callBack: (Boolean, String) -> Unit) {
    ParseUser.logInInBackground(
        user,
        pass
    ) { parseUser: ParseUser?, parseException: ParseException? ->
        if (parseUser != null) {
            Toast.makeText(context, "Welcome back $user !", Toast.LENGTH_LONG).show()
            callBack.invoke(true, "")
        } else {
            callBack.invoke(
                false, if (parseException != null) {
                    parseException.message.toString()
                } else {
                    "Please try again letter"
                }
            )

        }
    }
}

fun forgotPassword(view: View, email: String, callBack: (Boolean) -> Unit) {
    ParseUser.requestPasswordResetInBackground(
        email
    ) { e ->
        if (e == null) {

            callBack.invoke(true)

            val toast = Toast.makeText(
                view.context,
                "An email was successfully sent with reset instructions.",
                Toast.LENGTH_LONG
            )
//            toast.setGravity(Gravity.TOP, 0, 250)
            toast.show()
        } else {

            callBack.invoke(false)

            val toast = Toast.makeText(
                view.context,
                "Something went wrong! Please try again later.",
                Toast.LENGTH_LONG
            )
//            toast.setGravity(Gravity.TOP, 0, 250)
            toast.show()
        }
    }
}

fun hideKeyboard(activity: FragmentActivity?, context: Context) {
    activity?.currentFocus?.let { view ->
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}