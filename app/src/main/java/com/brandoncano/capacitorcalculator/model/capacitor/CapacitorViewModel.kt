package com.brandoncano.capacitorcalculator.model.capacitor

import androidx.lifecycle.ViewModel
import com.brandoncano.capacitorcalculator.data.CapacitorValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CapacitorViewModel : ViewModel() {

    private val _capacitor = MutableStateFlow(Capacitor())
    val capacitor: StateFlow<Capacitor> get() = _capacitor

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    fun clear() {
        _capacitor.value = Capacitor()
        _isError.value = false
    }

    fun updateValues(value: String, capacitorValue: CapacitorValue) {
        // validate input first
        // update other 3 values if valid
        when (capacitorValue) {
            CapacitorValue.Code -> TODO("compute: uf, nf, pf")
            CapacitorValue.NF -> TODO()
            CapacitorValue.PF -> TODO()
            CapacitorValue.UF -> TODO()
        }
    }

    fun updateErrorState() {

    }
}
