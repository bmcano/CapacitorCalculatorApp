package com.brandoncano.capacitorcalculator.model

import com.brandoncano.capacitorcalculator.components.CapacitorTolerance
import com.brandoncano.capacitorcalculator.util.MultiplierFromDigit

/**
 * Job: Holds capacitor values and methods to compute each other value
 */
data class Capacitor(
    var code: String = "",
    var pf: String = "",
    var nf: String = "",
    var uf: String = "",
    var tolerance: CapacitorTolerance? = null,
    var units: String = "",
) {
    fun isEmpty(): Boolean {
        return code.isEmpty()
    }

    fun computeFromCode() {
        if (code.isEmpty()) return
        val number = if (code.length == 3) code.dropLast(1).toInt() else code.toInt()
        val multiplier = if (code.length == 3) code.takeLast(1) else "0"
        val pico = number * MultiplierFromDigit.execute(multiplier)
        pf = "$pico"
        nf = "${pico.toDouble() / 1000}"
        uf = "${pico.toDouble() / 1000000}"
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
        if (tolerance != null) {
            return "$code${tolerance?.name}\n${uf}μF\n${nf}nF\n${pf}pF\n${tolerance?.percentage}"
        }
        return "$code\n${uf}μF\n${nf}nF\n${pf}pF"
    }
}
