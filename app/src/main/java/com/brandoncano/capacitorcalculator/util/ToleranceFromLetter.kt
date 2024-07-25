package com.brandoncano.capacitorcalculator.util

/**
 * Job: Takes a letter and returns the tolerance
 * Notes:
 *   B, C -> SMD only
 *   P -> Ceramic only
 *   All others -> Ceramic and SMD
 */
object ToleranceFromLetter {

    private val table = mapOf(
        "B" to "0.1%", "C" to "0.25%", "D" to "0.5%", "F" to "1%", "G" to "2%", "H" to "3%",
        "J" to "5%", "K" to "10%", "M" to "20%", "P" to "+100%/-0%", "Z" to "+80%/-20%",
    )

    fun execute(letter: String): String {
        return table[letter] ?: ""
    }
}
