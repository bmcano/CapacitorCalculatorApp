package com.brandoncano.capacitorcalculator.model

import androidx.lifecycle.ViewModel
import com.brandoncano.capacitorcalculator.util.CapacitorValues

class CapacitorViewModel : ViewModel() {

    var capacitor = Capacitor()
    var fieldValues: FieldValues = FieldValues.Code
    var isError = false

    fun calculateCapacitance() {
        isError = !CapacitorValues.update(capacitor, fieldValues)
    }

    fun clearFields() {
        capacitor.clear()
        isError = false
    }
}
