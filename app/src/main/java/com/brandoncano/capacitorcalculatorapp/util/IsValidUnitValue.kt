package com.brandoncano.capacitorcalculatorapp.util

object IsValidUnitValue {

    fun execute(value: String, unit: String): Boolean {
        val capacitance = value.toIntOrNull() ?: 0


        return false
    }
}