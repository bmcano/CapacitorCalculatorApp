package com.brandoncano.capacitorcalculatorapp.constants

/**
 * Job: Used so it can be known which text field was most recently edited with an exhausted when
 */
sealed class FieldValues {
    object Code : FieldValues()
    object PF : FieldValues()
    object NF : FieldValues()
    object UF : FieldValues()
}
