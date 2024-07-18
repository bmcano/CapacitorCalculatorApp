package com.brandoncano.capacitorcalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brandoncano.capacitorcalculator.components.CapacitorTolerance

/**
 * Job: ViewModel for the home screen holding the capacitor components
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

    fun updatePf(value: String) {
        capacitor.value = capacitor.value?.copy(pf = value)
    }

    fun updateNf(value: String) {
        capacitor.value = capacitor.value?.copy(nf = value)
    }

    fun updateUf(value: String) {
        capacitor.value = capacitor.value?.copy(uf = value)
    }

    fun updateTolerance(value: CapacitorTolerance?) {
        capacitor.value = capacitor.value?.copy(tolerance = value)
    }

    fun calculateValues() {
        capacitor.value?.computeFromCode()
        capacitor.value?.computeFromPF()
        capacitor.value?.computeFromNF()
        capacitor.value?.computeFromUF()
    }
}
