package com.singhar.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.ybq.android.spinkit.SpinKitView
import com.singhar.R
open class CommonActivity : AppCompatActivity() {

    var spin_kit : SpinKitView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_common)
        spin_kit = findViewById<View>(R.id.spin_kit) as SpinKitView
    }

    fun showLoadingBar(showLoader: Boolean) {

        if (showLoader) {
            spin_kit?.visibility = View.VISIBLE
        } else {
            spin_kit?.visibility = View.GONE
        }

    }
}