package com.brandoncano.capacitorcalculator.util

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object PlayStore {

    private const val CAPACITOR_LINK = "https://play.google.com/store/apps/details?id=com.brandoncano.capacitorcalculator"
    private const val RESISTOR_LINK = "https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator"

    fun openCapacitorApp(context: Context) {
        open(context, CAPACITOR_LINK)
    }

    fun openResistorApp(context: Context) {
        open(context, RESISTOR_LINK)
    }

    private fun open(context: Context, link: String) {
        try {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(link))
        } catch (e: Exception) {
            e.printStackTrace()
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
                .setMessage("A problem occurred while trying to open the link.")
                .setPositiveButton("Close", null)
                .show()
        }
    }
}
