package com.brandoncano.capacitorcalculator.util

import com.brandoncano.capacitorcalculator.components.Tolerance

object ToleranceValues {
    fun get(): List<Tolerance> {
        return listOf(
            Tolerance.D,
            Tolerance.F,
            Tolerance.G,
            Tolerance.H,
            Tolerance.J,
            Tolerance.K,
            Tolerance.M,
            Tolerance.P,
            Tolerance.Z,
        )
    }
}