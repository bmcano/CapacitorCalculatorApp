package com.brandoncano.capacitorcalculator.components

/**
 * Job: holds all the different types of SMD capacitor coding systems
 */
sealed class SmdMode(val name: String) {
    data object ThreeDigit : SmdMode("3 Digit EIA")
    data object FourDigit : SmdMode("4 Digit EIA")
    data object EIA198 : SmdMode("EIA-198")
}
