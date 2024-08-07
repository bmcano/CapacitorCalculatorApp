package com.brandoncano.capacitorcalculator.util

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.brandoncano.capacitorcalculator.constants.Links

/**
 * Job: Opens a web view of the desired link to navigate to
 */
object OpenLink {

    fun openCapacitorApp(context: Context) {
        open(context, Links.CAPACITOR_PLAYSTORE)
    }

    fun openInductorApp(context: Context) {
        open(context, Links.INDUCTOR_PLAYSTORE)
    }

    fun openResistorApp(context: Context) {
        open(context, Links.RESISTOR_PLAYSTORE)
    }

    fun openPrivacyPolicy(context: Context) {
        open(context, Links.PRIVACY_POLICY)
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
