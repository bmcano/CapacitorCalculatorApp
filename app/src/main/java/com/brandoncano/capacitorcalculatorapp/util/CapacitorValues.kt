package com.brandoncano.capacitorcalculatorapp.util

import android.util.Log
import com.brandoncano.capacitorcalculatorapp.constants.Capacitor
import com.brandoncano.capacitorcalculatorapp.constants.FieldValues

object CapacitorValues {

    fun update(capacitor: Capacitor, fieldValues: FieldValues) {
        when (fieldValues) {
            FieldValues.Code -> {
                val isValid = IsValidCode.execute(capacitor.code)
                if (isValid && capacitor.code.isNotEmpty()) {
                    capacitor.computeFromCode()
                }
            }
            FieldValues.PF -> {
                val isValid = IsValidCapacitance.checkPF(capacitor.pf)
                Log.e("Brandon", "$isValid")
                if (isValid && capacitor.pf.isNotEmpty()) {
                    capacitor.computeFromPF()
                }
            }
            FieldValues.NF -> {
                val isValid = IsValidCapacitance.checkNF(capacitor.nf)
                if (isValid && capacitor.nf.isNotEmpty()) {
                    capacitor.computeFromNF()
                }
            }
            FieldValues.UF -> {
                val isValid = IsValidCapacitance.checkUF(capacitor.uf)
                if (isValid && capacitor.uf.isNotEmpty()) {
                    capacitor.computeFromUF()
                }
            }
        }
    }
}