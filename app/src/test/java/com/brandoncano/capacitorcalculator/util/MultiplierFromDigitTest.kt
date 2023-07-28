package com.brandoncano.capacitorcalculator.util

import org.junit.Assert.assertEquals
import org.junit.Test

class MultiplierFromDigitTest {

    @Test
    fun `standard multiplier values`() {
        val multipliers = listOf("0", "1", "2", "3", "4", "5", "6")
        val values = listOf(1, 10, 100, 1000, 10000, 100000, 1000000)
        for (i in 0..6) {
            val result = MultiplierFromDigit.execute(multipliers[i])
            assertEquals(values[i], result)
        }
    }

    @Test
    fun `non-standard multiplier values`() {
        val multipliers = listOf("7", "8", "9", "10", "digit", "multiplier")
        multipliers.forEach {
            val result = MultiplierFromDigit.execute(it)
            assertEquals(1, result)
        }
    }
}