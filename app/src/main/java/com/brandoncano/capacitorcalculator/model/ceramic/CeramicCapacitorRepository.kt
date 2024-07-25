package com.brandoncano.capacitorcalculator.model.ceramic

import android.content.Context
import com.brandoncano.capacitorcalculator.components.SharedPreferences

class CeramicCapacitorRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: CeramicCapacitorRepository? = null
        fun getInstance(context: Context): CeramicCapacitorRepository = instance
            ?: synchronized(this) {
                CeramicCapacitorRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.CODE_INPUT_CERAMIC.clearData(application)
        SharedPreferences.CAPACITANCE_INPUT_CERAMIC.clearData(application)
        SharedPreferences.UNITS_DROPDOWN_CERAMIC.clearData(application)
        SharedPreferences.TOLERANCE_DROPDOWN_CERAMIC.clearData(application)
    }

    fun loadCapacitor(): CeramicCapacitor {
        val code = SharedPreferences.CODE_INPUT_CERAMIC.loadData(application)
        val capacitance = SharedPreferences.CAPACITANCE_INPUT_CERAMIC.loadData(application)
        val units = SharedPreferences.UNITS_DROPDOWN_CERAMIC.loadData(application)
        val tolerance = SharedPreferences.TOLERANCE_DROPDOWN_CERAMIC.loadData(application)
        return CeramicCapacitor(code, capacitance, tolerance, units)
    }

    fun saveCapacitor(capacitor: CeramicCapacitor) {
        SharedPreferences.CODE_INPUT_CERAMIC.saveData(application, capacitor.code)
        SharedPreferences.CAPACITANCE_INPUT_CERAMIC.saveData(application, capacitor.capacitance)
        SharedPreferences.UNITS_DROPDOWN_CERAMIC.saveData(application, capacitor.units)
        SharedPreferences.TOLERANCE_DROPDOWN_CERAMIC.saveData(application, capacitor.tolerance)
    }
}
