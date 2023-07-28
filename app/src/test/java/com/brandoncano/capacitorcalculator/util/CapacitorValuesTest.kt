package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.components.Capacitor
import com.brandoncano.capacitorcalculator.components.FieldValues
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CapacitorValuesTest {

    @Test
    fun `testing from an empty code`() {
        val capacitor = Capacitor()
        val fieldValues: FieldValues = FieldValues.Code
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor(), capacitor)
    }

    @Test
    fun `testing from an incorrect code`() {
        val capacitor = Capacitor(code = "127")
        val fieldValues: FieldValues = FieldValues.Code
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertFalse(result)
        assertEquals(Capacitor(code = "127"), capacitor)
    }

    @Test
    fun `testing from an correct code`() {
        val capacitor = Capacitor(code = "123")
        val fieldValues: FieldValues = FieldValues.Code
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor("123", "12000", "12.0", "0.012"), capacitor)
    }

    @Test
    fun `testing from an empty pf`() {
        val capacitor = Capacitor()
        val fieldValues: FieldValues = FieldValues.PF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor(), capacitor)
    }

    @Test
    fun `testing from an incorrect pf`() {
        val capacitor = Capacitor(pf = "127")
        val fieldValues: FieldValues = FieldValues.PF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertFalse(result)
        assertEquals(Capacitor(pf = "127"), capacitor)
    }

    @Test
    fun `testing from an correct pf`() {
        val capacitor = Capacitor(pf = "12000")
        val fieldValues: FieldValues = FieldValues.PF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor("123", "12000", "12.0", "0.012"), capacitor)
    }

    @Test
    fun `testing from an empty nf`() {
        val capacitor = Capacitor()
        val fieldValues: FieldValues = FieldValues.NF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor(), capacitor)
    }

    @Test
    fun `testing from an incorrect nf`() {
        val capacitor = Capacitor(nf = "127")
        val fieldValues: FieldValues = FieldValues.NF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertFalse(result)
        assertEquals(Capacitor(nf = "127"), capacitor)
    }

    @Test
    fun `testing from an correct nf`() {
        val capacitor = Capacitor(nf = "12")
        val fieldValues: FieldValues = FieldValues.NF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor("123", "12000.0", "12", "0.012"), capacitor)
    }

    @Test
    fun `testing from an empty uf`() {
        val capacitor = Capacitor()
        val fieldValues: FieldValues = FieldValues.UF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor(), capacitor)
    }

    @Test
    fun `testing from an incorrect uf`() {
        val capacitor = Capacitor(uf = "127")
        val fieldValues: FieldValues = FieldValues.UF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertFalse(result)
        assertEquals(Capacitor(uf = "127"), capacitor)
    }

    @Test
    fun `testing from an correct uf`() {
        val capacitor = Capacitor(uf = "0.012")
        val fieldValues: FieldValues = FieldValues.UF
        val result = CapacitorValues.update(capacitor, fieldValues)
        assertTrue(result)
        assertEquals(Capacitor("123", "12000.0", "12.0", "0.012"), capacitor)
    }
}
