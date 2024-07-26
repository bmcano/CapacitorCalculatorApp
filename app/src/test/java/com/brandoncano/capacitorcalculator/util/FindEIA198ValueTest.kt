package com.brandoncano.capacitorcalculator.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FindEIA198ValueTest {

    @Test
    fun testValidValues() {
        val validValues = mapOf(
            "A" to 1.0, "B" to 1.1, "C" to 1.2, "D" to 1.3, "E" to 1.5, "F" to 1.6, "G" to 1.8,
            "H" to 2.0, "J" to 2.2, "K" to 2.4, "a" to 2.6, "L" to 2.7, "M" to 3.0, "N" to 3.3,
            "b" to 3.5, "P" to 3.6, "Q" to 3.9, "d" to 4.0, "R" to 4.3, "e" to 4.5, "S" to 4.7,
            "f" to 5.0, "T" to 5.1, "U" to 5.6, "m" to 6.0, "V" to 6.2, "W" to 6.8, "n" to 7.0,
            "X" to 7.5, "t" to 8.0, "Y" to 8.2, "y" to 9.0, "Z" to 9.1
        )

        for ((key, value) in validValues) {
            assertEquals(value, FindEIA198Value.execute(key), 0.0)
        }
    }

    @Test
    fun testInvalidValues() {
        val invalidValues = listOf(" ", "AA", "1", "", "z", "!", "@", "#", "\n", "null")
        for (invalidValue in invalidValues) {
            assertTrue(FindEIA198Value.execute(invalidValue).isNaN())
        }
    }
}
