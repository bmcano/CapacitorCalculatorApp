package com.brandoncano.capacitorcalculator.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.lang.Exception

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object EmailFeedback {

    fun execute(context: Context) {
        val uri = Uri.parse("mailto:brandoncano.development@gmail.com?subject=[Feedback] - Capacitor Calculator")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        try {
            val title = "Send email"
            context.startActivity(Intent.createChooser(intent, title))
        } catch (ex: Exception) {
            ex.printStackTrace()
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
                .setMessage("A problem occurred when trying to send an email.")
                .setPositiveButton("Close", null)
                .show()
        }
    }
}
