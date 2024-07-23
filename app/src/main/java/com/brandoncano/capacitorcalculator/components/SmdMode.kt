package com.brandoncano.capacitorcalculator.components

sealed class SmdMode {
    data object ThreeDigit : SmdMode()
    data object FourDigit : SmdMode()
    data object EIA198 : SmdMode()
}