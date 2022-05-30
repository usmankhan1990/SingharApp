package com.singhar.screens.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.parse.ParseUser
import com.singhar.MainActivity
import com.singhar.R
import com.singhar.common.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.edtEmail
import kotlinx.android.synthetic.main.activity_signup.edtPassword
import kotlinx.android.synthetic.main.activity_signup.layout_loading


class SignUpActivity : FragmentActivity() {

    var isParlourSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)

        flYes.setBackgroundColor(this.getColor(R.color.white))
        flNo.setBackgroundColor(this.getColor(R.color.dark_blue))
        txtYes.setTextColor(this.getColor(R.color.dark_blue))
        txtNo.setTextColor(this.getColor(R.color.white))
        clBeautySignUp.visibility = View.GONE

        flYes.setOnClickListener {
            clBeautySignUp.visibility = View.VISIBLE
            flYes.setBackgroundColor(this.getColor(R.color.dark_blue))
            flNo.setBackgroundColor(this.getColor(R.color.white))
            txtYes.setTextColor(this.getColor(R.color.white))
            txtNo.setTextColor(this.getColor(R.color.dark_blue))
            scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            isParlourSelected = true
        }

        flNo.setOnClickListener {
            clBeautySignUp.visibility = View.GONE
            flYes.setBackgroundColor(this.getColor(R.color.white))
            flNo.setBackgroundColor(this.getColor(R.color.dark_blue))
            txtYes.setTextColor(this.getColor(R.color.dark_blue))
            txtNo.setTextColor(this.getColor(R.color.white))
            isParlourSelected = false
        }

        btnSubmit.setOnClickListener { view ->

            val password = edtPassword.text.toString()
            val userName = edtName.text.toString()
            val phoneNumber = edtPhone.text.toString()

            if (password.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Kindly put valid Password", Toast.LENGTH_SHORT
                ).show()
            } else {
                if (emailValidator(edtEmail, context = view.context) && userName.isNotEmpty() && phoneNumberValidator(edtPhone, context = view.context)) {

                    val userEmail = edtEmail.text.toString()

                    edtEmail.setTextColor(view.context.getColor(R.color.dark_blue))
                    edtName.setTextColor(view.context.getColor(R.color.dark_blue))

                    layout_loading.visibility = View.VISIBLE

                    signUp(userEmail = userEmail, userName = userName, password, phoneNumber = phoneNumber, this) { isSuccess, message ->

                        layout_loading.visibility = View.GONE

                        if (isSuccess) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            ParseUser.logOut()

                            customDialog(
                                context = view.context,
                                title = "Sign Up Failed",
                                description = "Please enter valid Email & Password.",
                                drawable = R.drawable.alert,
                                layoutInflater = layoutInflater
                            )
                        }
                    }
                } else {
                    edtEmail.setTextColor(view.context.getColor(R.color.red))
                    edtName.setTextColor(view.context.getColor(R.color.red))
                    Toast.makeText(
                        view.context,
                        "Kindly put valid Password, Name, Phone Number & Email", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}