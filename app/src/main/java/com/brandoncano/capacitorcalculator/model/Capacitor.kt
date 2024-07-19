package com.brandoncano.capacitorcalculator.model

import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage

/**
 * Job: Holds capacitor values and methods to compute each other value
 */
data class Capacitor(
    var code: String = "",
    var pf: String = "",
    var nf: String = "",
    var uf: String = "",
    var tolerance: String = "",
    var units: String = "",
) {
    fun isEmpty(): Boolean {
        return code.isEmpty()
    }

    fun computeFromPF() {
        if (pf.isEmpty()) return
        nf = "${pf.toDouble() / 1000}"
        uf = "${pf.toDouble() / 1000000}"
        computeCode()
    }

    fun computeFromNF() {
        if (nf.isEmpty()) return
        pf = (nf.toDouble() * 1000).toBigDecimal().toPlainString()
        uf = "${nf.toDouble() / 1000}"
        computeCode()
    }

    fun computeFromUF() {
        if (uf.isEmpty()) return
        pf = (uf.toDouble() * 1000000).toBigDecimal().toPlainString()
        nf = "${uf.toDouble() * 1000}"
        computeCode()
    }

    private fun computeCode() {
        val firstTwoDigits = pf.take(2)
        val multiplier = if (pf.endsWith(".0")) {
            pf.drop(2).dropLast(2).count { it == '0' }
        } else {
            pf.drop(2).count { it == '0' }
        }
        code = "$firstTwoDigits$multiplier"
    }

    override fun toString(): String {
        return "$code$tolerance\n${this.formatCapacitance()} ${this.getTolerancePercentage()}"
    }
}
