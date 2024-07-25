package com.brandoncano.capacitorcalculator.model.smd

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SmdCapacitorViewModel(context: Context): ViewModel() {

    private val repository = SmdCapacitorRepository.getInstance(context)
    private val capacitor = MutableLiveData<SmdCapacitor>()

    init {
        capacitor.value = SmdCapacitor()
    }

    override fun onCleared() {
        capacitor.value = null
    }

    fun clear() {
        capacitor.value = SmdCapacitor(navBarSelection = getNavBarSelection())
        repository.clear()
    }

    fun getCapacitorLiveData(): LiveData<SmdCapacitor> {
        capacitor.value = repository.loadCapacitor()
        return capacitor
    }

    fun updateCode(value: String) {
        capacitor.value = capacitor.value?.copy(code = value)
    }

    fun updateUnits(value: String) {
        capacitor.value = capacitor.value?.copy(units = value)
    }

    fun getNavBarSelection(): Int {
        val capacitor = repository.loadCapacitor()
        return capacitor.navBarSelection
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..2)
        capacitor.value = capacitor.value?.copy(navBarSelection = navBarSelection)
        repository.saveNavBarSelection(navBarSelection)
    }

    fun saveCapacitorValues(capacitor: SmdCapacitor) {
        repository.saveCapacitor(capacitor)
    }
}
