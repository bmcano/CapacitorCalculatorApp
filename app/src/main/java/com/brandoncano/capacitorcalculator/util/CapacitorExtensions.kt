package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.ceramic.CeramicCapacitor

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
    return !when (this.units) {
        Units.PF -> IsValidCapacitance.checkPF(this.capacitance)
        Units.NF -> IsValidCapacitance.checkNF(this.capacitance)
        Units.UF -> IsValidCapacitance.checkUF(this.capacitance)
        else -> IsValidCapacitance.checkPF(this.capacitance)
    }
}

fun CeramicCapacitor.formatCode(): String {
    return when (this.units) {
        Units.PF -> CodeFormatter.computeFromPF(this.capacitance)
        Units.NF -> CodeFormatter.computeFromNF(this.capacitance)
        Units.UF -> CodeFormatter.computeFromUF(this.capacitance)
        else -> CodeFormatter.computeFromPF(this.capacitance)
    }
}