package com.brandoncano.capacitorcalculator.util

object IsValidCapacitance {

    fun checkPF(capacitance: String): Boolean {
        if (capacitance.isEmpty()) return true
        val pf = capacitance.toDoubleOrNull() ?: return false
        return validate(pf)
    }

    fun checkNF(capacitance: String): Boolean {
        if (capacitance.isEmpty()) return true
        val nf = capacitance.toDoubleOrNull() ?: return false
        val pf = nf * 1000
        return validate(pf)
    }

    fun checkUF(capacitance: String): Boolean {
        if (capacitance.isEmpty()) return true
        val uf = capacitance.toDoubleOrNull() ?: return false
        val pf = uf * 1000000
        return validate(pf)
    }

    private fun validate(capacitance: Double): Boolean {
        if (capacitance < 10.0 || capacitance > 99000000.0) return false
        val cap = capacitance.toInt().toString()
        var count = 0
        cap.forEach { if (it != '0' && it != '.') count++ }
        return count <= 2
    }
}
