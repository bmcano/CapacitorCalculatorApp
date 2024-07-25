package com.brandoncano.capacitorcalculator.util

import org.junit.Assert.assertEquals
import org.junit.Test

class ToleranceFromLetterTest {

    @Test
    fun testValidValues() {
        val validValues = mapOf(
            "B" to "0.1%", "C" to "0.25%", "D" to "0.5%", "F" to "1%", "G" to "2%", "H" to "3%",
            "J" to "5%", "K" to "10%", "M" to "20%", "P" to "+100%/-0%", "Z" to "+80%/-20%",
        )

        for ((key, value) in validValues) {
            assertEquals(value, ToleranceFromLetter.execute(key))
        }
    }

    @Test
    fun testInvalidValues() {
        val invalidValues = listOf(" ", "AA", "1", "", "!", "@", "#", "\n", "null")
        for (invalidValue in invalidValues) {
            assertEquals("", ToleranceFromLetter.execute(invalidValue))
        }
    }
}
