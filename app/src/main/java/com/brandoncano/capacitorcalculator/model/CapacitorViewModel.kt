package com.brandoncano.capacitorcalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Job: ViewModel for the ceramic capacitor screen holding the capacitor components
 */
class CapacitorViewModel : ViewModel() {

    private val capacitor = MutableLiveData<Capacitor>()

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

    fun updateCapacitance(value: String) {
        capacitor.value = capacitor.value?.copy(capacitance = value)
    }

    fun updateUnits(value: String) {
        capacitor.value = capacitor.value?.copy(units = value)
    }

    fun updateTolerance(value: String) {
        capacitor.value = capacitor.value?.copy(tolerance = value)
    }

    fun saveCapacitorValues(capacitor: Capacitor) {
        // TODO - add repo
    }
}
