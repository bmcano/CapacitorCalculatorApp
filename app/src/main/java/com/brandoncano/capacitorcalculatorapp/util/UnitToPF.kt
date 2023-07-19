package com.brandoncano.capacitorcalculatorapp.util

import com.brandoncano.capacitorcalculatorapp.constants.Units

/**
 * Job: Takes in one of the 3 units and gives the multiplier to convert to pF for calculations.
 */
object UnitToPF {

    fun execute(unit: Units): Int {
        return when (unit) {
            Units.PF -> 1
            Units.NF -> 1000
            Units.UF -> 1000000
        }
    }
}
