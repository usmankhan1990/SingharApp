package com.singhar.screens.signup

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.FragmentActivity
import com.singhar.R
import kotlinx.android.synthetic.main.activity_signup.*


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

        btnSubmit.setOnClickListener {

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}