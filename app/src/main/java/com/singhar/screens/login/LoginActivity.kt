package com.singhar.screens.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.parse.ParseUser
import com.singhar.MainActivity
import com.singhar.R
import com.singhar.common.customDialog
import com.singhar.common.emailValidator
import com.singhar.common.login
import com.singhar.screens.forgot.ForgotPasswordActivity
import com.singhar.screens.signup.SignUpActivity
import com.singhar.screens.webview.GeneralWebview
import kotlinx.android.synthetic.main.activity_login.edtEmail
import kotlinx.android.synthetic.main.activity_login.edtPassword
import kotlinx.android.synthetic.main.activity_login.layout_loading
import kotlinx.android.synthetic.main.activity_login.txtForgetPassword
import kotlinx.android.synthetic.main.activity_login.txtSignUp

class LoginActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtTermsConditions = findViewById<TextView>(R.id.txtTermsConditions)

        btnLogin.setOnClickListener { view ->

            val password = edtPassword.text.toString()

            if (password.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Kindly put valid Password", Toast.LENGTH_SHORT
                ).show()
            } else {
                if (emailValidator(edtEmail, context = view.context)) {

                    val username = edtEmail.text.toString()

                    edtEmail.setTextColor(view.context.getColor(R.color.dark_blue))

                    layout_loading.visibility = View.VISIBLE

                    login(username, password, this) { isSuccess, message ->

                        layout_loading.visibility = View.GONE

                        if (isSuccess) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            ParseUser.logOut()

                            customDialog(
                                context = view.context,
                                title = "Login Failed",
                                description = "Please enter valid Email & Password.",
                                drawable = R.drawable.alert,
                                layoutInflater = layoutInflater
                            )
                        }
                    }
                } else {
                    edtEmail.setTextColor(view.context.getColor(R.color.red))
                }
            }
        }

        txtForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        txtTermsConditions.setOnClickListener {
            val intent = Intent(this, GeneralWebview::class.java)
            startActivity(intent)
        }

        txtSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
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
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}