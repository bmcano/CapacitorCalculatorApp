package com.brandoncano.capacitorcalculator.util

/**
 * Job: Determine if the code is valid.
 *
 * Note:
 *  - length is limited to 3 already
 *  - if length is 1 or 2 then any number is valid
 *  - if length is 3, then the 3rd digit must be between 0-6
 */
object IsValidCode {

    fun execute(code: String): Boolean {
        val length = code.length
        if (length > 3) {
            return false
        }
        if (length < 3) {
            return true
        }
        // should always be valid
        val thirdDigit: Int = code[2].digitToIntOrNull() ?: 0
        return thirdDigit in 0..6
    }
}