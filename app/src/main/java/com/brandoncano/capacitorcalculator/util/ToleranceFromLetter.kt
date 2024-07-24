package com.brandoncano.capacitorcalculator.util

object ToleranceFromLetter {

    fun execute(letter: String): String {
        return when (letter) {
            "B" -> "0.1%" // SMD only
            "C" -> "0.25%" // SMD only
            "D" -> "0.5%"
            "F" -> "1%"
            "G" -> "2%"
            "H" -> "3%"
            "J" -> "5%"
            "K" -> "10%"
            "M" -> "20%"
            "P" -> "+100%/-0%" // ceramic only
            "Z" -> "+80%/-20%"
            else -> ""
        }
    }
}