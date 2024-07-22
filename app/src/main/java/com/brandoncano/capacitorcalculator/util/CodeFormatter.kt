package com.brandoncano.capacitorcalculator.util

/**
 * Job: Compute a 3 digit code from a capacitance value
 */
object CodeFormatter {

    fun computeFromPF(pf: String): String {
        if (pf.isEmpty()) return ""
        return computeCode(pf)
    }

    fun computeFromNF(nf: String): String {
        if (nf.isEmpty()) return ""
        val pf = (nf.toDouble() * 1000).toBigDecimal().toPlainString()
        return computeCode(pf)
    }

    fun computeFromUF(uf: String): String {
        if (uf.isEmpty()) return ""
        val pf = (uf.toDouble() * 1000000).toBigDecimal().toPlainString()
        return computeCode(pf)
    }

    private fun computeCode(pf: String): String {
        val firstTwoDigits = pf.take(2)
        val multiplier = if (pf.endsWith(".0")) {
            pf.drop(2).dropLast(2).count { it == '0' }
        } else {
            pf.drop(2).count { it == '0' }
        }
        return "$firstTwoDigits$multiplier"
    }
}