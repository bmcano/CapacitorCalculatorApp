package com.brandoncano.capacitorcalculator.model.ceramic

import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage

data class CeramicCapacitor(
    var code: String = "",
    var capacitance: String = "",
    var tolerance: String = "",
    var units: String = "",
) {
    fun isEmpty(isCode: Boolean = true): Boolean {
        if (isCode) {
            return code.isEmpty() || code.length < 2
        }
        return capacitance.isEmpty() || (units == Units.PF && capacitance.length < 2)
    }

    override fun toString(): String {
        // TODO - this only accounts for Code to Capacitance and not the other way around
        return "$code$tolerance\n${this.formatCapacitance()} ${this.getTolerancePercentage()}"
    }
}
