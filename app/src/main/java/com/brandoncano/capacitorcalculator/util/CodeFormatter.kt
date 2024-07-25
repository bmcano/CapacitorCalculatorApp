package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units

/**
 * Job: Compute a 3 digit code from a capacitance value and its' unit
 */
object CodeFormatter {

    fun execute(capacitance: String, units: String): String {
        if (capacitance.isEmpty()) return ""
        val pf = when (units) {
            Units.NF -> (capacitance.toDouble() * 1000).toBigDecimal().toPlainString()
            Units.UF -> (capacitance.toDouble() * 1000000).toBigDecimal().toPlainString()
            else -> capacitance
        }
        val firstTwoDigits = pf.take(2)
        val multiplier = if (pf.endsWith(".0")) {
            pf.drop(2).dropLast(2).count { it == '0' }
        } else {
            pf.drop(2).count { it == '0' }
        }
        return "$firstTwoDigits$multiplier"
    }
}
