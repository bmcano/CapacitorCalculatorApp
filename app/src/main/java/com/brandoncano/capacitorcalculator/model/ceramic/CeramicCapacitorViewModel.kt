package com.brandoncano.capacitorcalculator.model.ceramic

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Job: ViewModel for the ceramic capacitor screen holding the capacitor components
 */
class CeramicCapacitorViewModel(context: Context) : ViewModel() {

    private val repository = CeramicCapacitorRepository.getInstance(context)
    private val capacitor = MutableLiveData<CeramicCapacitor>()

    init {
        capacitor.value = CeramicCapacitor()
    }

    override fun onCleared() {
        capacitor.value = null
    }

    fun clear() {
        capacitor.value = CeramicCapacitor()
        repository.clear()
    }

    fun getCapacitorLiveData(): LiveData<CeramicCapacitor> {
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

    fun saveCapacitorValues(capacitor: CeramicCapacitor) {
        repository.saveCapacitor(capacitor)
    }
}
