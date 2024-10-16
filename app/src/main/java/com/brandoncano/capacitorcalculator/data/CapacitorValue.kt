package com.brandoncano.capacitorcalculator.data

sealed class CapacitorValue {
    data object Code : CapacitorValue()
    data object UF : CapacitorValue()
    data object NF : CapacitorValue()
    data object PF : CapacitorValue()
}
