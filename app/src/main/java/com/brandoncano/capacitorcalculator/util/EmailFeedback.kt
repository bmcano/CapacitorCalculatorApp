package com.brandoncano.capacitorcalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object EmailFeedback {

    fun execute(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("mailto:brandoncano.development@gmail.com?subject="
                + Uri.encode("[Feedback] - Capacitor Calculator")
                + "&body="
        )
        context.startActivity(intent)
    }
}
