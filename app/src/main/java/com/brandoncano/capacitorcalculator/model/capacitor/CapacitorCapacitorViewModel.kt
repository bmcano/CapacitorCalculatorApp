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

    fun updateValues(code: String, units: String, tolerance: String, voltageRating: String) {
        capacitor.value = capacitor.value
            ?.copy(code = code, units = units, tolerance = tolerance, voltageRating = voltageRating)
    }

    fun updateValues(capacitance: String, units: String, tolerance: String) {
        capacitor.value = capacitor.value
            ?.copy(capacitance = capacitance, units = units, tolerance = tolerance)
    }


    fun saveCapacitorValues(capacitor: Capacitor) {
        repository.saveCapacitor(capacitor)
    }
}
