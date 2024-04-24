package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.model.Capacitor
import com.brandoncano.capacitorcalculator.model.FieldValues

object CapacitorValues {

    fun update(capacitor: Capacitor, fieldValues: FieldValues): Boolean {
        when (fieldValues) {
            FieldValues.Code -> {
                val isValid = IsValidCode.execute(capacitor.code)
                if (isValid && capacitor.code.isNotEmpty()) {
                    capacitor.computeFromCode()
                }
                return isValid
            }
            FieldValues.PF -> {
                val isValid = IsValidCapacitance.checkPF(capacitor.pf)
                if (isValid && capacitor.pf.isNotEmpty()) {
                    capacitor.computeFromPF()
                }
                return isValid
            }
            FieldValues.NF -> {
                val isValid = IsValidCapacitance.checkNF(capacitor.nf)
                if (isValid && capacitor.nf.isNotEmpty()) {
                    capacitor.computeFromNF()
                }
                return isValid
            }
            FieldValues.UF -> {
                val isValid = IsValidCapacitance.checkUF(capacitor.uf)
                if (isValid && capacitor.uf.isNotEmpty()) {
                    capacitor.computeFromUF()
                }
                return isValid
            }
        }
    }
}