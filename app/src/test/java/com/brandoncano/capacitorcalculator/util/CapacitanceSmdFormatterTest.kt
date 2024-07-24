package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.components.SmdMode
import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitor
import com.brandoncano.capacitorcalculator.util.CapacitanceSmdFormatter.execute
import org.junit.Assert.assertEquals
import org.junit.Test


class CapacitanceSmdFormatterTest {

    @Test
    fun testExecuteEmptyCapacitor() {
        // TODO - since we generally check for valid code prior we might remove the need for this
        val capacitor = SmdCapacitor()
        assertEquals("Enter code", execute(capacitor))
        capacitor.code = "1"
        assertEquals("Enter code", execute(capacitor))
    }

    @Test
    fun testInvalidCodes() {
        val capacitor = SmdCapacitor("AAA", Units.PF, 0)
        assertEquals("NaN", execute(capacitor))
        capacitor.code = " aa"
        assertEquals("NaN", execute(capacitor))
    }

    @Test
    fun testValid3DigitCodes() {
        // since 3 and 4 are mostly the same we leave 'R' test cases for 4 digit
        val capacitor = SmdCapacitor("150", Units.PF, 0)
        assertEquals("15 ${Units.PF}", execute(capacitor))
        capacitor.code = "151"
        assertEquals("150 ${Units.PF}", execute(capacitor))
        capacitor.code = "152"
        assertEquals("1500 ${Units.PF}", execute(capacitor))
        capacitor.code = "153"
        assertEquals("15000 ${Units.PF}", execute(capacitor))
        capacitor.code = "154"
        assertEquals("150000 ${Units.PF}", execute(capacitor))
        capacitor.code = "155"
        assertEquals("1500000 ${Units.PF}", execute(capacitor))
        capacitor.code = "156"
        assertEquals("1.5E7 ${Units.PF}", execute(capacitor))
        capacitor.code = "157"
        assertEquals("1.5E8 ${Units.PF}", execute(capacitor))
        capacitor.code = "158"
        assertEquals("1.5E9 ${Units.PF}", execute(capacitor))
        capacitor.code = "159"
        assertEquals("1.5E10 ${Units.PF}", execute(capacitor))
        capacitor.units = Units.NF
        capacitor.code = "160"
        assertEquals("0.016 ${Units.NF}", execute(capacitor))
        capacitor.code = "161"
        assertEquals("0.16 ${Units.NF}", execute(capacitor))
        capacitor.code = "162"
        assertEquals("1.6 ${Units.NF}", execute(capacitor))
        capacitor.code = "163"
        assertEquals("16 ${Units.NF}", execute(capacitor))
        capacitor.code = "164"
        assertEquals("160 ${Units.NF}", execute(capacitor))
        capacitor.code = "165"
        assertEquals("1600 ${Units.NF}", execute(capacitor))
        capacitor.code = "166"
        assertEquals("16000 ${Units.NF}", execute(capacitor))
        capacitor.code = "167"
        assertEquals("160000 ${Units.NF}", execute(capacitor))
        capacitor.code = "168"
        assertEquals("1600000 ${Units.NF}", execute(capacitor))
        capacitor.code = "169"
        assertEquals("1.6E7 ${Units.NF}", execute(capacitor))
        capacitor.units = Units.UF
        capacitor.code = "190"
        assertEquals("1.9E-5 ${Units.UF}", execute(capacitor))
        capacitor.code = "191"
        assertEquals("1.9E-4 ${Units.UF}", execute(capacitor))
        capacitor.code = "192"
        assertEquals("0.0019 ${Units.UF}", execute(capacitor))
        capacitor.code = "193"
        assertEquals("0.019 ${Units.UF}", execute(capacitor))
        capacitor.code = "194"
        assertEquals("0.19 ${Units.UF}", execute(capacitor))
        capacitor.code = "195"
        assertEquals("1.9 ${Units.UF}", execute(capacitor))
        capacitor.code = "196"
        assertEquals("19 ${Units.UF}", execute(capacitor))
        capacitor.code = "197"
        assertEquals("190 ${Units.UF}", execute(capacitor))
        capacitor.code = "198"
        assertEquals("1900 ${Units.UF}", execute(capacitor))
        capacitor.code = "199"
        assertEquals("19000 ${Units.UF}", execute(capacitor))
    }

    @Test
    fun testValid4DigitCodes() {
        val capacitor = SmdCapacitor("130B", Units.PF, 1)
        assertEquals("13 ${Units.PF} (0.1%)", execute(capacitor))
        capacitor.code = "130C"
        assertEquals("13 ${Units.PF} (0.25%)", execute(capacitor))
        capacitor.code = "1R8D"
        assertEquals("1.8 ${Units.PF} (0.5%)", execute(capacitor))
        capacitor.code = "1R8F"
        capacitor.units = Units.NF
        assertEquals("0.0018 ${Units.NF} (1%)", execute(capacitor))
        capacitor.code = "1R8G"
        // TODO - fix this test - will need to add sigfig check to util
//        capacitor.units = Units.UF
//        assertEquals("1.8E ${Units.UF} (2%)", execute(capacitor))
        capacitor.units = Units.PF
        capacitor.code = "130J"
        assertEquals("13 ${Units.PF} (5%)", execute(capacitor))
        capacitor.code = "130K"
        assertEquals("13 ${Units.PF} (10%)", execute(capacitor))
        capacitor.code = "130M"
        assertEquals("13 ${Units.PF} (20%)", execute(capacitor))
        capacitor.code = "130Z"
        assertEquals("13 ${Units.PF} (+80%/-20%)", execute(capacitor))
    }

    @Test
    fun testValidEIA198Codes() {
        testEIA198Value("A0", 1.0)
        testEIA198Value("B1", 1.1 * 10)
        testEIA198Value("C2", 1.2 * 100)
        testEIA198Value("D3", 1.3 * 1000)
        testEIA198Value("E4", 1.5 * 10000)
        testEIA198Value("F5", 1.6 * 100000)
        testEIA198Value("G6", 1.8 * 1000000)
        testEIA198Value("H7", 2.0 * 10000000)
        testEIA198Value("J8", 2.2 * 100000000)
        testEIA198Value("K9", 2.4 * 0.1)
        testEIA198Value("a0", 2.6)
        testEIA198Value("L1", 2.7 * 10)
        testEIA198Value("M2", 3.0 * 100)
        testEIA198Value("N3", 3.3 * 1000)
        testEIA198Value("b4", 3.5 * 10000)
        testEIA198Value("P5", 3.6 * 100000)
        testEIA198Value("Q6", 3.9 * 1000000)
        testEIA198Value("d7", 4.0 * 10000000)
        testEIA198Value("R8", 4.3 * 100000000)
        testEIA198Value("e9", 4.5 * 0.1)
        testEIA198Value("S0", 4.7)
        testEIA198Value("f1", 5.0 * 10)
        testEIA198Value("T2", 5.1 * 100)
        testEIA198Value("U3", 5.6 * 1000)
        testEIA198Value("m4", 6.0 * 10000)
        testEIA198Value("V5", 6.2 * 100000)
        testEIA198Value("W6", 6.8 * 1000000)
        testEIA198Value("n7", 7.0 * 10000000)
        testEIA198Value("X8", 7.5 * 100000000)
        testEIA198Value("t9", 8.0 * 0.1)
        testEIA198Value("Y0", 8.2)
        testEIA198Value("y1", 9.0 * 10)
        testEIA198Value("Z2", 9.1 * 100)
    }

    private fun testEIA198Value(code: String, expected: Double) {
        val capacitor = SmdCapacitor(code, Units.PF, 2)
        assertEquals("$expected ${Units.PF}}", execute(capacitor))
    }
}


