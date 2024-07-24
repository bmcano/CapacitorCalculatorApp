package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.components.SmdMode

/**
 * Job: Checks for a valid SMD capacitor code depending on the mode.
 */
object IsValidSmdCode {

    fun execute(code: String, mode: SmdMode): Boolean {
        // we return true here since the calculation for capacitance won't unless proper length
        val length = code.length
        if (length < 2 ||
            (mode is SmdMode.ThreeDigit && length < 3) ||
            (mode is SmdMode.FourDigit && length < 4)
            ) {
            return true
        }
        val regex3 = Regex("^[1-9][0-9R][0-9]$")
        val regex4 = Regex("^[1-9][0-9R][0-9][BCDFGJKMZ]$")
        val regex198 = Regex("^[A-NP-Zabdefmnty][0-9]$")
        val isValidRCount = code.count { it == 'R'} < 2
        return when (mode) {
            SmdMode.ThreeDigit -> regex3.matches(code)
            SmdMode.FourDigit -> regex4.matches(code) && isValidRCount
            SmdMode.EIA198 -> regex198.matches(code)
        }
    }


}