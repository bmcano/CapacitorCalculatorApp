package com.brandoncano.capacitorcalculator.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsValidCodeTest {

    @Test
    fun `valid 1 digit codes`() {
        for (i in 0..9) {
            val result = IsValidCode.execute(i.toString())
            assertTrue(result)
        }
    }

    @Test
    fun `valid 2 digit codes`() {
        for (i in 10..99) {
            val result = IsValidCode.execute(i.toString())
            assertTrue(result)
        }
    }

    @Test
    fun `valid 3 digit codes`() {
        for (m in 0..6) {
            for (i in 10..99) {
                val result = IsValidCode.execute("$i$m")
                assertTrue(result)
            }
        }
    }

    @Test
    fun `empty string input is true but does not update other values`() {
        val result = IsValidCode.execute("")
        assertTrue(result)
    }

    @Test
    fun `invalid 3 digit inputs`() {
        for (m in 7..9) {
            for (i in 10..99) {
                val result = IsValidCode.execute("$i$m")
                assertFalse(result)
            }
        }
    }

    @Test
    fun `decimal invalid inputs`() {
        val codes = listOf("1.20", "12.0", "123.0", "100.4")
        codes.forEach {
            assertFalse(IsValidCode.execute(it))
        }
    }

    @Test
    fun `other invalid inputs`() {
        val codes = listOf("strings", "12a", "YXZ", "(1)")
        codes.forEach {
            assertFalse(IsValidCode.execute(it))
        }
    }
}
