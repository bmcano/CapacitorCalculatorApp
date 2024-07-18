package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.model.Capacitor
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel

fun Capacitor.isCodeInvalid(): Boolean {
    return !IsValidCode.execute(this.code)
}

fun Capacitor.formatCapacitance(): String {
    return CapacitanceFormatter.execute(this)
}