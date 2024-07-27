package com.brandoncano.capacitorcalculator.model.standard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandardCapacitorViewModel: ViewModel() {

    private var capacitor = MutableLiveData<StandardCapacitor>()

    init {
        capacitor.value = StandardCapacitor()
    }

    override fun onCleared() {
        capacitor.value = null
    }

    fun clear() {
        capacitor.value = StandardCapacitor()
    }

    fun getCapacitorLiveData(): LiveData<StandardCapacitor> {
        return capacitor
    }

    fun updateCode(value: String) {
        capacitor.value = capacitor.value?.copy(code = value)
    }

    fun updateUnits(value: String) {
        capacitor.value = capacitor.value?.copy(units = value)
    }

    fun updateTolerance(value: String) {
        capacitor.value = capacitor.value?.copy(tolerance = value)
    }

    fun updateVoltageRating(value: String) {
        capacitor.value = capacitor.value?.copy(voltageRating = value)
    }
}
