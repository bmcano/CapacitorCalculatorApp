package com.brandoncano.capacitorcalculator.model.smd

import com.brandoncano.capacitorcalculator.data.SmdMode
import com.brandoncano.capacitorcalculator.util.formatCapacitance

data class SmdCapacitor(
    var code: String = "",
    var units: String = "",
    var navBarSelection: Int = 0,
) {
    fun isEmpty(): Boolean {
        val length = code.length
        return when (getSmdMode()) {
            SmdMode.ThreeDigit -> length < 3
            SmdMode.FourDigit -> length < 4
            SmdMode.EIA198 -> length < 2
        }
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
        val type = "SMD Capacitor: ${getSmdMode().name}"
        val capacitance = this.formatCapacitance()
        return "$type\nCode = $code\nCapacitance = $capacitance"
    }
}
