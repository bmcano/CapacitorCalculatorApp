package com.brandoncano.capacitorcalculator.model.ceramic

import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.formatCode
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage

data class CeramicCapacitor(
    var code: String = "",
    var capacitance: String = "",
    var tolerance: String = "",
    var units: String = "",
) {
    var isCapacitanceToCode = false

    fun isEmpty(isCode: Boolean = true): Boolean {
        if (isCode) {
            return code.isEmpty() || code.length < 2
        }
        return capacitance.isEmpty() || (units == Units.PF && capacitance.length < 2)
    }

    override fun toString(): String {
        val type = "Ceramic Capacitor:"
        if (isCapacitanceToCode) {
            return "$type\nCode = ${formatCode()}\nCapacitance = $capacitance $units"
        }
        return "$type\nCode = $code$tolerance\nCapacitance = ${formatCapacitance()} ${getTolerancePercentage()}"
    }
}
