package com.brandoncano.capacitorcalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object PlayStore {

    private const val CAPACITOR_LINK = "https://play.google.com/store/apps/details?id=com.brandoncano.capacitorcalculator"
    private const val RESISTOR_LINK = "https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator"

    fun openCapacitorApp(context: Context) {
        val uri = Uri.parse(CAPACITOR_LINK)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    fun openResistorApp(context: Context) {
        val uri = Uri.parse(RESISTOR_LINK)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}
