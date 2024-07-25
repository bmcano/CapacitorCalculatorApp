package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsValidCapacitanceTest {

    @Test
    fun `check empty and short inputs inputs`() {
        var emptyResult = IsValidCapacitance.execute("", Units.PF)
        assertTrue(emptyResult)
        emptyResult = IsValidCapacitance.execute("", Units.NF)
        assertTrue(emptyResult)
        emptyResult = IsValidCapacitance.execute("", Units.UF)
        assertTrue(emptyResult)
        // returns true here because the input it too small so calculation does not occur
        assertTrue(IsValidCapacitance.execute("1", Units.PF))
    }

    @Test
    fun `check valid pf inputs`() {
        assertTrue(IsValidCapacitance.execute("10", Units.PF))
        assertTrue(IsValidCapacitance.execute("23.0", Units.PF))
        assertTrue(IsValidCapacitance.execute("450", Units.PF))
        assertTrue(IsValidCapacitance.execute("6700", Units.PF))
        assertTrue(IsValidCapacitance.execute("89000", Units.PF))
        assertTrue(IsValidCapacitance.execute("130000", Units.PF))
        assertTrue(IsValidCapacitance.execute("4600000", Units.PF))
        assertTrue(IsValidCapacitance.execute("79000000", Units.PF))
        assertTrue(IsValidCapacitance.execute("99000000", Units.PF))
    }

    @Test
    fun `check valid nf inputs`() {
        assertTrue(IsValidCapacitance.execute(".010", Units.NF))
        assertTrue(IsValidCapacitance.execute("0.01", Units.NF))
        assertTrue(IsValidCapacitance.execute(".023", Units.NF))
        assertTrue(IsValidCapacitance.execute("0.023", Units.NF))
        assertTrue(IsValidCapacitance.execute(".45", Units.NF))
        assertTrue(IsValidCapacitance.execute("0.45", Units.NF))
        assertTrue(IsValidCapacitance.execute("1", Units.NF))
        assertTrue(IsValidCapacitance.execute("6.7", Units.NF))
        assertTrue(IsValidCapacitance.execute("89.0", Units.NF))
        assertTrue(IsValidCapacitance.execute("130", Units.NF))
        assertTrue(IsValidCapacitance.execute("4600", Units.NF))
        assertTrue(IsValidCapacitance.execute("79000", Units.NF))
        assertTrue(IsValidCapacitance.execute("99000", Units.NF))
    }

    @Test
    fun `check valid uf inputs`() {
        assertTrue(IsValidCapacitance.execute(".00001", Units.UF))
        assertTrue(IsValidCapacitance.execute("0.00001", Units.UF))
        assertTrue(IsValidCapacitance.execute(".000023", Units.UF))
        assertTrue(IsValidCapacitance.execute("0.00045", Units.UF))
        assertTrue(IsValidCapacitance.execute(".0067", Units.UF))
        assertTrue(IsValidCapacitance.execute(".089", Units.UF))
        assertTrue(IsValidCapacitance.execute("0.130", Units.UF))
        assertTrue(IsValidCapacitance.execute("1", Units.UF))
        assertTrue(IsValidCapacitance.execute("4.6", Units.UF))
        assertTrue(IsValidCapacitance.execute("79.0", Units.UF))
        assertTrue(IsValidCapacitance.execute("99", Units.UF))
    }

    @Test
    fun `check invalid inputs`() {
        val values = listOf("abc", "1.1.1", "   ", "(1)", "1a", "990000000", "123", " 120", "\n120")
        values.forEach {
            assertFalse(IsValidCapacitance.execute(it, Units.PF))
            assertFalse(IsValidCapacitance.execute(it, Units.NF))
            assertFalse(IsValidCapacitance.execute(it, Units.UF))
        }
    }
}
