package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units

object MultiplierFromUnits {

    fun execute(units: String): Int {
        return when (units) {
            Units.PF -> 1
            Units.NF -> 1000
            Units.UF -> 1000000
            else -> 1 // pF by default
        }
    }
}