package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.constants.Units
import org.junit.Assert.assertEquals
import org.junit.Test

class MultiplierFromUnitsTest {

    @Test
    fun testUnits() {
        assertEquals(1, MultiplierFromUnits.execute(Units.PF))
        assertEquals(1000, MultiplierFromUnits.execute(Units.NF))
        assertEquals(1000000, MultiplierFromUnits.execute(Units.UF))
    }

    @Test
    fun testOtherInputs() {
        assertEquals(1, MultiplierFromUnits.execute("string"))
    }
}
