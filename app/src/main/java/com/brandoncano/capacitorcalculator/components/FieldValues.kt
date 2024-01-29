package com.brandoncano.capacitorcalculator.components

/**
 * Job: Used so it can be known which text field was most recently edited with an exhausted when
 */
sealed class FieldValues {
    data object Code : FieldValues()
    data object PF : FieldValues()
    data object NF : FieldValues()
    data object UF : FieldValues()
}
