package com.brandoncano.capacitorcalculator.model.capacitor

import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.formatCode
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage
import com.brandoncano.capacitorcalculator.util.getVoltageRating

data class Capacitor(
    var code: String = "",
    var capacitance: String = "",
    var tolerance: String = "",
    var units: String = "",
    val voltageRating: String = "",
) {
    var isCapacitanceToCode = false

    fun isEmpty(isCode: Boolean = true): Boolean {
        if (isCode) {
            return code.isEmpty() || code.length < 2
        }
        return capacitance.isEmpty() || (units == Units.PF && capacitance.length < 2)
    }

    override fun toString(): String {
        if (isCapacitanceToCode) {
            val code = "Code = ${formatCode()}$tolerance $voltageRating"
            val capacitance = "Capacitance = ${this.capacitance} $units}"
            val tolerance = "Tolerance = ${getTolerancePercentage()}"
            val voltageRating = "Voltage = ${getVoltageRating()}"
            return "$code\n$capacitance\n$tolerance\n$voltageRating".trimEnd(' ')
        }
        val code = "Code = $code$tolerance $voltageRating"
        val capacitance = "Capacitance = ${formatCapacitance()}"
        val tolerance = "Tolerance = ${getTolerancePercentage()}"
        val voltageRating = "Voltage = ${getVoltageRating()}"
        return "$code\n$capacitance\n$tolerance\n$voltageRating".trimEnd(' ')
    }
}
