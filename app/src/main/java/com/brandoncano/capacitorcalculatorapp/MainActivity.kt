package com.brandoncano.capacitorcalculatorapp

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.widget.doOnTextChanged
import com.brandoncano.capacitorcalculatorapp.util.IsValidCode

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInputCode: EditText
    private lateinit var editTextInputPicoF: EditText
    private lateinit var editTextInputNanoF: EditText
    private lateinit var editTextInputMicroF: EditText

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
        calculateButtonSetup()
    }

    private fun setup() {
        editTextInputCode = findViewById(R.id.input_enter_code)
        editTextInputPicoF = findViewById(R.id.input_enter_pf)
        editTextInputNanoF = findViewById(R.id.input_enter_nf)
        editTextInputMicroF = findViewById(R.id.input_enter_uf)

        editTextInputCode.doOnTextChanged { text, _, _, _, ->
            val code = text.toString()
            if (IsValidCode.execute(code)) {
                editTextInputCode.error = null
            } else {
                editTextInputCode.error = getString(R.string.invalid_input)
            }
        }

        editTextInputPicoF.doOnTextChanged { text, _, _, _, ->

        }

        editTextInputNanoF.doOnTextChanged { text, _, _, _, ->

        }

        editTextInputMicroF.doOnTextChanged { text, _, _, _, ->

        }
    }

    private fun calculateButtonSetup() {
        val calculateButton: Button = findViewById(R.id.button_calculate)
        calculateButton.setOnClickListener {
            closeKeyboard()
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}