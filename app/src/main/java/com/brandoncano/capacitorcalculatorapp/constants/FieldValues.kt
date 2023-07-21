package com.brandoncano.capacitorcalculatorapp.constants

sealed class FieldValues {
    object Code : FieldValues()
    object PF : FieldValues()
    object NF : FieldValues()
    object UF : FieldValues()
}