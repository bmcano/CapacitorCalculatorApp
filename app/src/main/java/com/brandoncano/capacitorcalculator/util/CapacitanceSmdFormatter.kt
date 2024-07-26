package com.brandoncano.capacitorcalculator.util

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.capacitorcalculator.components.SmdMode
import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitor

/**
 * Job: Take a SMD code, its' type, and format the capacitance based on unit
 */
object CapacitanceSmdFormatter {

    fun execute(capacitor: SmdCapacitor): String {
        if (capacitor.isEmpty()) return ""
        val smdMode = capacitor.getSmdMode()
        val code = capacitor.code
        val capacitance = when(smdMode) {
            SmdMode.ThreeDigit -> standard(code)
            SmdMode.FourDigit -> standard(code)
            SmdMode.EIA198 -> eia198(code)
        }
        val units = capacitor.units.ifEmpty { Units.PF }
        val unitsConversion = MultiplierFromUnits.execute(units)
        if (capacitance.isNaN()) {
            return "$capacitance"
        }
        val convertedCapacitance = (capacitance / unitsConversion).toString()
        val formattedCapacitance = formatCapacitance(convertedCapacitance)
        if (smdMode is SmdMode.FourDigit) {
            val tolerance = ToleranceFromLetter.execute(code[3].toString())
            return "$formattedCapacitance $units ($tolerance)"
        }
        return "$formattedCapacitance $units"
    }

    private fun standard(code: String): Double {
        val first = code[0]
        val second = code[1]
        return if (second == 'R') {
            val third = code[2]
            "$first.$third".toDoubleOrNull() ?: Double.NaN
        } else {
            val multiplier = MultiplierFromDigit.execute(code[2].toString())
            return ("$first$second".toDoubleOrNull() ?: Double.NaN) * multiplier
        }
    }

    private fun eia198(code: String): Double {
        val baseValue = FindEIA198Value.execute(code[0].toString())
        val multiplier = if (code[1] == '9') {
            0.1
        } else {
            MultiplierFromDigit.execute(code[1].toString()).toDouble()
        }
        return baseValue * multiplier
    }

    private fun formatCapacitance(capacitance: String): String {
        if (capacitance.endsWith(".0")) {
            return capacitance.removeSuffix(".0")
        }
        try {
            val sigFigs = SignificantFigures(capacitance)
            if (sigFigs.numberSignificantFigures > 2) {
                val value = sigFigs.setNumberSignificantFigures(2)
                return value.toString()
            }
        } catch (e: NumberFormatException) {
            return capacitance
        }
        return capacitance
    }
}
