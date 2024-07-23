package com.brandoncano.capacitorcalculator.model.smd

import com.brandoncano.capacitorcalculator.components.SmdMode

data class SmdCapacitor(
    var code: String = "",
    var units: String = "",
    var navBarSelection: Int = 0,
) {
    fun isEmpty(): Boolean {
        // TODO
        return false
    }

    fun getSmdMode(): SmdMode {
        return when (navBarSelection) {
            0 -> SmdMode.ThreeDigit
            1 -> SmdMode.FourDigit
            2 -> SmdMode.EIA198
            else -> SmdMode.ThreeDigit
        }
    }

    override fun toString(): String {
        // TODO
        return "SMD Resistor Code: $code\nResistance:"
    }
}
