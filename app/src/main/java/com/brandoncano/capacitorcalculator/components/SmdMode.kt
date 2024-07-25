package com.brandoncano.capacitorcalculator.components

/**
 * Job: holds all the different types of SMD capacitor coding systems
 */
sealed class SmdMode {
    data object ThreeDigit : SmdMode()
    data object FourDigit : SmdMode()
    data object EIA198 : SmdMode()
}
