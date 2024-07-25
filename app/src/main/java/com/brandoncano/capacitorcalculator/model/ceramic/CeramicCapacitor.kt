package com.brandoncano.capacitorcalculator.model.ceramic

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
        return capacitance.isEmpty()
    }

    override fun toString(): String {
        return "$code$tolerance\n${this.formatCapacitance()} ${this.getTolerancePercentage()}"
    }
}
