package com.brandoncano.capacitorcalculator.components

/**
 * Notes:
 *   B, C -> SMD only
 *   P -> Capacitor only
 *   All others -> Capacitor and SMD
 */
enum class Tolerance(val letter: String, val tolerance: String) {
    B("B", "±0.1%"),
    C("C", "±0.25%"),
    D("D", "±0.5%"),
    F("F", "±1%"),
    G("G", "±2%"),
    H("H", "±3%"),
    J("J", "±5%"),
    K("K", "±10%"),
    M("M", "±20%"),
    P("P", "+100%/-0%"),
    Z("Z", "+80%/-20%");

    companion object {
        fun getStandardToleranceList(): List<String> {
            return entries.filter { it != B && it != C }.map { it.letter }
        }

        fun getToleranceValue(letter: String): String {
            return entries.find { it.letter == letter }?.tolerance ?: ""
        }
    }
}
