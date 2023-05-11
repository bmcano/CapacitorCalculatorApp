package com.brandoncano.capacitorcalculatorapp

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.widget.doOnTextChanged

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
    }

    private fun setup() {
        editTextInputCode = findViewById(R.id.input_enter_code)
        editTextInputPicoF = findViewById(R.id.input_enter_pf)
        editTextInputNanoF = findViewById(R.id.input_enter_nf)
        editTextInputMicroF = findViewById(R.id.input_enter_uf)

        editTextInputCode.doOnTextChanged { text, _, _, _, ->

        }

        editTextInputPicoF.doOnTextChanged { text, _, _, _, ->

        }

        editTextInputNanoF.doOnTextChanged { text, _, _, _, ->

        }

        editTextInputMicroF.doOnTextChanged { text, _, _, _, ->

        }
    }
}