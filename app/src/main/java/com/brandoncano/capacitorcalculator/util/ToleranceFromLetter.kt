package com.brandoncano.capacitorcalculator.util

/**
 * Job: Takes a letter and returns the tolerance
 * Notes:
 *   B, C -> SMD only
 *   P -> Ceramic only
 *   All others -> Ceramic and SMD
 */
object ToleranceFromLetter {

    fun execute(letter: String): String {
        return when (letter) {
            "B" -> "0.1%"
            "C" -> "0.25%"
            "D" -> "0.5%"
            "F" -> "1%"
            "G" -> "2%"
            "H" -> "3%"
            "J" -> "5%"
            "K" -> "10%"
            "M" -> "20%"
            "P" -> "+100%/-0%"
            "Z" -> "+80%/-20%"
            else -> ""
        }
    }
}