package com.brandoncano.capacitorcalculator.model.capacitor

import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage
import com.brandoncano.capacitorcalculator.util.getVoltageRating

data class Capacitor(
    val code: String = "",
    val units: String = "",
    val tolerance: String = "",
    val voltageRating: String = "",
) {
    fun isEmpty(): Boolean {
        return code.length < 3
    }

    override fun toString(): String {
        val code = "Code = $code$tolerance $voltageRating"
        val capacitance = "Capacitance = ${formatCapacitance()}"
        val tolerance = "Tolerance = ${getTolerancePercentage()}"
        val voltageRating = "Voltage = ${getVoltageRating()}"
        return "$code\n$capacitance\n$tolerance\n$voltageRating".trimEnd(' ')
    }
}
