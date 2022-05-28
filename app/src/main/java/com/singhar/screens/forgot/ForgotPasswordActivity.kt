package com.singhar.screens.forgot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.singhar.R
import com.singhar.common.customDialog
import com.singhar.common.emailValidator
import com.singhar.common.forgotPassword
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        setContentView(R.layout.activity_forgot_password)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener { view ->

            val username = edtEmail.text.toString()

            if (emailValidator(edtEmail, context = view.context)) {

                edtEmail.setTextColor(view.context.getColor(R.color.dark_blue))

                layout_loading.visibility = View.VISIBLE

                forgotPassword(view, username) { isSuccess ->
                    layout_loading.visibility = View.GONE

                    customDialog(
                        context = view.context,
                        title = "Password Sent",
                        description = "An email was successfully sent with reset instructions.",
                        layoutInflater = layoutInflater,
                        drawable = R.drawable.refresh_email,
                    )
                }
            } else {
                edtEmail.setTextColor(view.context.getColor(R.color.red))
            }
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}