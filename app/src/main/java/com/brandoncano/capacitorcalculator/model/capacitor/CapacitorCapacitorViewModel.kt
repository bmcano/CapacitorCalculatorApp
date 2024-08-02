package com.brandoncano.capacitorcalculator.model.capacitor

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CapacitorCapacitorViewModel(context: Context) : ViewModel() {

    private val repository = CapacitorRepository.getInstance(context)
    private val capacitor = MutableLiveData<Capacitor>()

    init {
        capacitor.value = Capacitor()
    }

    override fun onCleared() {
        capacitor.value = null
    }

    fun clear() {
        capacitor.value = Capacitor()
        repository.clear()
    }

    fun getCapacitorLiveData(): LiveData<Capacitor> {
        capacitor.value = repository.loadCapacitor()
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

    fun updateVoltageRating(value: String) {
        capacitor.value = capacitor.value?.copy(voltageRating = value)
    }

    fun saveCapacitorValues(capacitor: Capacitor) {
        repository.saveCapacitor(capacitor)
    }
}
