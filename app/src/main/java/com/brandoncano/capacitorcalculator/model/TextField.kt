package com.brandoncano.capacitorcalculator.model

/**
 * Job: Used so it can be known which text field was most recently edited with an exhausted when
 */
sealed class TextField {
    data object CODE : TextField()
    data object PF : TextField()
    data object NF : TextField()
    data object UF : TextField()
}
