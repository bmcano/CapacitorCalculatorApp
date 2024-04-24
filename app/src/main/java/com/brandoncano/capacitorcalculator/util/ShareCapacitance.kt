package com.brandoncano.capacitorcalculator.util

import android.content.Context
import android.content.Intent
import com.brandoncano.capacitorcalculator.model.Capacitor

object ShareCapacitance {

    fun execute(capacitor: Capacitor, context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_TEXT, "$capacitor")
        context.startActivity(Intent.createChooser(intent, ""))
    }
}