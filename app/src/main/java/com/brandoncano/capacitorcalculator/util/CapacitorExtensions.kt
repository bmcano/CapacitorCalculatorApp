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