package com.brandoncano.capacitorcalculator.util

/**
 * Job: Takes the 3 digit and converts it to the correct multiplier to pF for calculations.
 */
object MultiplierFromDigit {

    fun execute(digit: String): Int {
        return when (digit) {
            "0" -> 1
            "1" -> 10
            "2" -> 100
            "3" -> 1000
            "4" -> 10000
            "5" -> 100000
            "6" -> 1000000
            else -> 1
        }
    }
}
