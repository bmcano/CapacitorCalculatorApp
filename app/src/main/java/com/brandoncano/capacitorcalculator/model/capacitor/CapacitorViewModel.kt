package com.brandoncano.capacitorcalculator.model.capacitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CapacitorViewModel: ViewModel() {

    private var capacitor = MutableLiveData<Capacitor>()

    init {
        capacitor.value = Capacitor()
    }

    override fun onCleared() {
        capacitor.value = null
    }

    fun clear() {
        capacitor.value = Capacitor()
    }

    fun getCapacitorLiveData(): LiveData<Capacitor> {
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
