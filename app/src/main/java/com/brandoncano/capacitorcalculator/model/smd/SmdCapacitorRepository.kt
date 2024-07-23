package com.brandoncano.capacitorcalculator.model.smd

import android.content.Context
import com.brandoncano.capacitorcalculator.components.SharedPreferences

class SmdCapacitorRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: SmdCapacitorRepository? = null
        fun getInstance(context: Context): SmdCapacitorRepository = instance
            ?: synchronized(this) {
                SmdCapacitorRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.CODE_INPUT_SMD.clearData(application)
        SharedPreferences.UNITS_DROPDOWN_SMD.clearData(application)
    }

    fun loadCapacitor(): SmdCapacitor {
        val code = SharedPreferences.CODE_INPUT_SMD.loadData(application)
        val units = SharedPreferences.UNITS_DROPDOWN_SMD.loadData(application)
        val number = SharedPreferences.NAVBAR_SELECTION_SMD.loadData(application)
        return SmdCapacitor(code, units, number.toIntOrNull() ?: 0)
    }

    fun saveCapacitor(capacitor: SmdCapacitor) {
        SharedPreferences.CODE_INPUT_SMD.saveData(application, capacitor.code)
        SharedPreferences.UNITS_DROPDOWN_SMD.saveData(application, capacitor.units)
    }

    fun saveNavBarSelection(number: Int) {
        SharedPreferences.NAVBAR_SELECTION_SMD.saveData(application, "$number")
    }
}
