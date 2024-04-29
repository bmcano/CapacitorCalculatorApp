package com.brandoncano.capacitorcalculator.model

import androidx.lifecycle.ViewModel
import com.brandoncano.capacitorcalculator.util.IsValidCapacitance
import com.brandoncano.capacitorcalculator.util.IsValidCode

/**
 * Job: ViewModel for the home screen holding the capacitor components
 */
class CapacitorViewModel : ViewModel() {

    var capacitor = Capacitor()
    var textField: TextField = TextField.CODE
    var isError = false

    fun calculateCapacitance() {
        val isValid = when(textField) {
            TextField.CODE -> IsValidCode.execute(capacitor.code)
            TextField.PF -> IsValidCapacitance.checkPF(capacitor.pf)
            TextField.NF -> IsValidCapacitance.checkNF(capacitor.nf)
            TextField.UF -> IsValidCapacitance.checkUF(capacitor.uf)
        }
        if (isValid) {
            when(textField) {
                TextField.CODE -> capacitor.computeFromCode()
                TextField.PF -> capacitor.computeFromPF()
                TextField.NF -> capacitor.computeFromNF()
                TextField.UF -> capacitor.computeFromUF()
            }
        }
        isError = !isValid
    }

    fun clearFields() {
        capacitor = Capacitor()
        isError = false
    }
}
