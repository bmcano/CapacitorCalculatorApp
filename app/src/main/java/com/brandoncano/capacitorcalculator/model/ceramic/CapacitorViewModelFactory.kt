package com.brandoncano.capacitorcalculator.model.ceramic

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brandoncano.capacitorcalculator.model.CeramicCapacitorViewModel

class CapacitorViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass.canonicalName) {
            CeramicCapacitorViewModel::class.java.canonicalName -> CeramicCapacitorViewModel(context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}