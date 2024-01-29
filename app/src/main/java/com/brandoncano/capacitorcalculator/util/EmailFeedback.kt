package com.brandoncano.capacitorcalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object EmailFeedback {

    fun execute(context: Context) {
        val uri = Uri.parse("mailto:brandoncano.development@gmail.com?subject=[Feedback] - Capacitor Calculator")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        context.startActivity(intent)
    }
}
