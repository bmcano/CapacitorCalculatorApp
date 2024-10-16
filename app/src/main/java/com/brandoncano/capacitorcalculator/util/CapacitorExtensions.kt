package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.data.Tolerance
import com.brandoncano.capacitorcalculator.data.VoltageRating
import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.capacitorlegacy.CapacitorLegacy
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitor

/**
 * Job: Holds the extension functions for all the capacitor models
 */

fun CapacitorLegacy.isCodeInvalid(): Boolean {
    return !IsValidCode.execute(this.code)
}

fun CapacitorLegacy.formatCapacitance(): String {
    return CapacitanceFormatter.execute(this.code, this.units)
}

fun CapacitorLegacy.getTolerancePercentage(): String {
    return Tolerance.getToleranceValue(this.tolerance)
}

fun CapacitorLegacy.getVoltageRating(): String {
    return VoltageRating.getVoltageValue(this.voltageRating)
}

fun CapacitorLegacy.isCapacitanceInvalid(): Boolean {
    val units = this.units.ifEmpty { Units.PF }
    return !IsValidCapacitance.execute(this.capacitance, units)
}

fun CapacitorLegacy.formatCode(): String {
    return CodeFormatter.execute(this.capacitance, this.units)
}

fun SmdCapacitor.isSmdInputInvalid(): Boolean {
    return !IsValidSmdCode.execute(this.code, this.getSmdMode())
}

fun SmdCapacitor.formatCapacitance(): String {
    return CapacitanceSmdFormatter.execute(this)
}
