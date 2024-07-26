package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.model.ceramic.CeramicCapacitor
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitor

/**
 * Job: Holds the extension functions for all the capacitor models
 */

fun CeramicCapacitor.isCodeInvalid(): Boolean {
    return !IsValidCode.execute(this.code)
}

fun CeramicCapacitor.formatCapacitance(): String {
    return CapacitanceFormatter.execute(this)
}

fun CeramicCapacitor.getTolerancePercentage(): String {
    return ToleranceFromLetter.execute(this.tolerance)
}

fun CeramicCapacitor.isCapacitanceInvalid(): Boolean {
    return !IsValidCapacitance.execute(this.capacitance, this.units)
}

fun CeramicCapacitor.formatCode(): String {
    return CodeFormatter.execute(this.capacitance, this.units)
}

fun SmdCapacitor.isSmdInputInvalid(): Boolean {
    return !IsValidSmdCode.execute(this.code, this.getSmdMode())
}

fun SmdCapacitor.formatCapacitance(): String {
    return CapacitanceSmdFormatter.execute(this)
}
