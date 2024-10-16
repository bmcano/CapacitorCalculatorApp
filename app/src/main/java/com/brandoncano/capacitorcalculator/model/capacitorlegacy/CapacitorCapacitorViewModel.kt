package com.brandoncano.capacitorcalculator.model.capacitorlegacy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CapacitorCapacitorViewModel(context: Context) : ViewModel() {

    private val repository = CapacitorRepository.getInstance(context)
    private val capacitorLegacy = MutableLiveData<CapacitorLegacy>()

    init {
        capacitorLegacy.value = CapacitorLegacy()
    }

    override fun onCleared() {
        capacitorLegacy.value = null
    }

    fun clear() {
        capacitorLegacy.value = CapacitorLegacy()
        repository.clear()
    }

    fun getCapacitorLiveData(): LiveData<CapacitorLegacy> {
        capacitorLegacy.value = repository.loadCapacitor()
        return capacitorLegacy
    }

    fun updateValues(code: String, units: String, tolerance: String, voltageRating: String) {
        capacitorLegacy.value = capacitorLegacy.value
            ?.copy(code = code, units = units, tolerance = tolerance, voltageRating = voltageRating)
    }

    fun updateValues(capacitance: String, units: String, tolerance: String) {
        capacitorLegacy.value = capacitorLegacy.value
            ?.copy(capacitance = capacitance, units = units, tolerance = tolerance)
    }


    fun saveCapacitorValues(capacitorLegacy: CapacitorLegacy) {
        repository.saveCapacitor(capacitorLegacy)
    }
}
