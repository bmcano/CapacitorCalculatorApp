package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.Capacitor

object CapacitanceFormatter {

    fun execute(capacitor: Capacitor): String {
        val code = capacitor.code
        if (code.isEmpty()) return "Enter code"
        val number = if (code.length == 3) code.dropLast(1).toInt() else code.toInt()
        val multiplier = if (code.length == 3) code.takeLast(1) else "0"
        val pico = number * MultiplierFromDigit.execute(multiplier)
        return when (capacitor.units) {
            Units.PF -> "$pico pF"
            Units.NF -> "${pico.toDouble() / 1000} nF"
            Units.UF -> "${pico.toDouble() / 1000000} µF"
            else -> "$pico pF"
        }
    }
}