package com.brandoncano.capacitorcalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

object PlayStore {

    // TODO - will have to switch this link out
    private const val capacitorPlayStoreLink = "https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator"
    private const val resistorPlayStoreLink = "https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator"

    fun openCapacitorApp(context: Context) {
        val uri = Uri.parse(capacitorPlayStoreLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        ContextCompat.startActivity(context, intent, null)
    }

    fun openResistorApp(context: Context) {
        val uri = Uri.parse(resistorPlayStoreLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        ContextCompat.startActivity(context, intent, null)
    }
}