package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.data.SmdMode

/**
 * Job: Checks for a valid SMD capacitor code depending on the mode.
 */
object IsValidSmdCode {

    fun execute(code: String, mode: SmdMode): Boolean {
        val length = code.length
        val length3digit = mode is SmdMode.ThreeDigit && length < 3
        val length4digit = mode is SmdMode.FourDigit && length < 4
        if (length < 2 || length3digit || length4digit) {
            // return true here since calculation won't happen unless it's a suitable length
            return true
        }
        val regex3 = Regex("^[1-9][0-9R][0-9]$")
        val regex4 = Regex("^[1-9][0-9R][0-9][BCDFGJKMZ]$")
        val regex198 = Regex("^[A-NP-Zabdefmnty][0-9]$")
        val isValidRCount = code.count { it == 'R' } < 2
        return when (mode) {
            SmdMode.ThreeDigit -> regex3.matches(code)
            SmdMode.FourDigit -> regex4.matches(code) && isValidRCount
            SmdMode.EIA198 -> regex198.matches(code)
        }
    }
}
