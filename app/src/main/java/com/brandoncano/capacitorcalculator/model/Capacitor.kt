package com.brandoncano.capacitorcalculator.model

import com.brandoncano.capacitorcalculator.util.MultiplierFromDigit

/**
 * Job: Holds capacitor values and methods to compute each other value
 */
data class Capacitor(
    var code: String = "",
    var pf: String = "",
    var nf: String = "",
    var uf: String = "",
    var tolerance: Tolerance? = null
) {
    fun clear() {
        code = ""
        pf = ""
        nf = ""
        uf = ""
        tolerance = null
    }

    fun computeFromCode() {
        val number = if (code.length == 3) code.dropLast(1).toInt() else code.toInt()
        val multiplier = if (code.length == 3) code.takeLast(1) else "0"
        val pico = number * MultiplierFromDigit.execute(multiplier)
        pf = "$pico"
        nf = "${pico.toDouble() / 1000}"
        uf = "${pico.toDouble() / 1000000}"
    }

    fun computeFromPF() {
        nf = "${pf.toDouble() / 1000}"
        uf = "${pf.toDouble() / 1000000}"
        computeCode()
    }

    fun computeFromNF() {
        pf = (nf.toDouble() * 1000).toBigDecimal().toPlainString()
        uf = "${nf.toDouble() / 1000}"
        computeCode()
    }

    fun computeFromUF() {
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
        return "$code\n${uf}μF\n${nf}nF\n${pf}pF"
    }
}
