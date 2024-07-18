package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.model.Capacitor
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel

fun Capacitor.isCodeInvalid(value: String): Boolean {
    return !IsValidCode.execute(value)
}

fun Capacitor.isPfInvalid(value: String): Boolean {
    return !IsValidCapacitance.checkPF(value)
}

fun Capacitor.isNfInvalid(value: String): Boolean {
    return !IsValidCapacitance.checkNF(value)
}

fun Capacitor.isUfInvalid(value: String): Boolean {
    return !IsValidCapacitance.checkUF(value)
}
