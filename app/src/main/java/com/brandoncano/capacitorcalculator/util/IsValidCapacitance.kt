package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units

/**
 * Job: takes a capacitance value and a string and determines if the value is valid
 */
object IsValidCapacitance {

    fun execute(capacitance: String, units: String): Boolean {
        capacitance.forEach { if (it.isWhitespace()) return false }
        if (capacitance.isEmpty() || (units == Units.PF && capacitance.length < 2)) return true
        val pf = when (units) {
            Units.NF -> (capacitance.toDoubleOrNull() ?: return false) * 1000
            Units.UF -> (capacitance.toDoubleOrNull() ?: return false) * 1000000
            else -> capacitance.toDoubleOrNull() ?: return false
        }
        if (pf < 10.0 || pf > 99000000.0) return false
        val cap = pf.toInt().toString()
        var count = 0
        cap.forEach { if (it != '0' && it != '.') count++ }
        return count <= 2
    }
}
