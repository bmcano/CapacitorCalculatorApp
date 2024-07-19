package com.brandoncano.capacitorcalculator.util

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
        "pF" -> IsValidCapacitance.checkPF(this.capacitance)
        "nF" -> IsValidCapacitance.checkNF(this.capacitance)
        "µF" -> IsValidCapacitance.checkUF(this.capacitance)
        else -> IsValidCapacitance.checkPF(this.capacitance)
    }
}

fun Capacitor.formatCode(): String {

    return ""
}