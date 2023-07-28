package com.brandoncano.capacitorcalculator.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsValidCapacitanceTest {

    @Test
    fun `check valid pF`() {
        val emptyResult = IsValidCapacitance.checkPF("")
        assertTrue(emptyResult)
    }

    @Test
    fun `check invalid pF`() {
        val values = listOf("string", "1.1.1", "   ", "(1)", "1a")
        values.forEach {
            val result = IsValidCapacitance.checkPF(it)
            assertFalse(result)
        }
    }

    @Test
    fun `check valid nF`() {
        val emptyResult = IsValidCapacitance.checkNF("")
        assertTrue(emptyResult)

        val result = IsValidCapacitance.checkNF("23.0")
        assertTrue(result)
    }

    @Test
    fun `check invalid nF`() {
        val values = listOf("string", "1.1.1", "   ", "(1)", "1a")
        values.forEach {
            val result = IsValidCapacitance.checkNF(it)
            assertFalse(result)
        }
    }

    @Test
    fun `check valid uF`() {
        val emptyResult = IsValidCapacitance.checkUF("")
        assertTrue(emptyResult)

        val result = IsValidCapacitance.checkUF("0.0056")
        assertTrue(result)
    }

    @Test
    fun `check invalid uF`() {
        val values = listOf("string", "1.1.1", "   ", "(1)", "1a")
        values.forEach {
            val result = IsValidCapacitance.checkUF(it)
            assertFalse(result)
        }
    }

    @Test
    fun `valid results from validate function`() {
        val values = listOf("12", "1200000", "1500", "85000")
        values.forEach {
            val result = IsValidCapacitance.checkPF(it)
            assertTrue(result)
        }
    }

    @Test
    fun `invalid results from validate function`() {
        val values = listOf("9", "1200000000", "1230")
        values.forEach {
            val result = IsValidCapacitance.checkPF(it)
            assertFalse(result)
        }
    }
}