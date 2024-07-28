package com.brandoncano.capacitorcalculator.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brandoncano.capacitorcalculator.model.ceramic.CeramicCapacitorViewModel
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitorViewModel
import com.brandoncano.capacitorcalculator.model.capacitor.CapacitorViewModel

class CapacitorViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass.canonicalName) {
            CeramicCapacitorViewModel::class.java.canonicalName -> CeramicCapacitorViewModel(context) as T
            SmdCapacitorViewModel::class.java.canonicalName -> SmdCapacitorViewModel(context) as T
            CapacitorViewModel::class.java.canonicalName -> CapacitorViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}
