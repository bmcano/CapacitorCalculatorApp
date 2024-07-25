package com.brandoncano.capacitorcalculator.util

import android.content.Context
import android.content.Intent

/**
 * Job: Takes a string and prepares it to share (or copy)
 */
object ShareCapacitance {

    fun execute(text: String, context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(intent, ""))
    }
}
