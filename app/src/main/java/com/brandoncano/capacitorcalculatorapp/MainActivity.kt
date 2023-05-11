package com.brandoncano.capacitorcalculatorapp

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.theme_blue_cyan))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.app_name)
            actionBar.elevation = 4F
        }
    }

    override fun onResume() {
        super.onResume()
        setup()
    }

    private fun setup() {

    }
}