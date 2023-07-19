package com.brandoncano.capacitorcalculatorapp.constants

sealed class Units(unit: String) {
    object PF : Units("pF")
    object NF : Units("nF")
    object UF : Units("µF")
}