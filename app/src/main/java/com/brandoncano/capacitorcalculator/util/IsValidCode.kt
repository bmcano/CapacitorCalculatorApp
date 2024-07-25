package com.brandoncano.capacitorcalculator.util

/**
 * Job: Determine if a ceramic capacitor code is valid.
 * Notes:
 *  - empty string will return true, but will not update any values
 *  - if length is 1 or 2 then any number is valid
 *  - if length is 3, then the 3rd digit must be between 0-6
 */
object IsValidCode {

    fun execute(code: String): Boolean {
        if (code.isEmpty()) return true
        code.toIntOrNull() ?: return false
        val length = code.length
        if (length != 3) {
            return length < 3
        }
        val thirdDigit: Int = code[2].digitToIntOrNull() ?: 0
        return thirdDigit in 0..6
    }
}
