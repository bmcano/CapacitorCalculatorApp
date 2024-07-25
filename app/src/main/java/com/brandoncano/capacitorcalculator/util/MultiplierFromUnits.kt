package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units

/**
 * Job: gets the multiplier to pF from a unit value
 */
object MultiplierFromUnits {

    fun execute(units: String): Int {
        return when (units) {
            Units.NF -> 1000
            Units.UF -> 1000000
            else -> 1 // Units.PF by default
        }
    }
}
