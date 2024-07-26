package com.brandoncano.capacitorcalculator.util

/**
 * Job: Takes a digit and converts it to the correct multiplier to pF for calculations.
 * Notes:
 *   0-6 -> Ceramic and SMD
 *   7-9 -> SMD
 */
object MultiplierFromDigit {

    fun execute(digit: String): Int {
        return when (digit) {
            "1" -> 10
            "2" -> 100
            "3" -> 1000
            "4" -> 10000
            "5" -> 100000
            "6" -> 1000000
            "7" -> 10000000
            "8" -> 100000000
            "9" -> 1000000000
            else -> 1 // "0" by default
        }
    }
}
