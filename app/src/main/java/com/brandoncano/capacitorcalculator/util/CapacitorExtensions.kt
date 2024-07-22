package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.Capacitor

fun Capacitor.isCodeInvalid(): Boolean {
    return !IsValidCode.execute(this.code)
}

fun Capacitor.formatCapacitance(): String {
    return CapacitanceFormatter.execute(this)
}

fun Capacitor.getTolerancePercentage(): String {
    return ToleranceFromLetter.execute(this.tolerance)
}

fun Capacitor.isCapacitanceInvalid(): Boolean {
    return !when (this.units) {
        Units.PF -> IsValidCapacitance.checkPF(this.capacitance)
        Units.NF -> IsValidCapacitance.checkNF(this.capacitance)
        Units.UF -> IsValidCapacitance.checkUF(this.capacitance)
        else -> IsValidCapacitance.checkPF(this.capacitance)
    }
}

fun Capacitor.formatCode(): String {
    return when (this.units) {
        Units.PF -> CodeFormatter.computeFromPF(this.capacitance)
        Units.NF -> CodeFormatter.computeFromNF(this.capacitance)
        Units.UF -> CodeFormatter.computeFromUF(this.capacitance)
        else -> CodeFormatter.computeFromPF(this.capacitance)
    }
}