package com.brandoncano.capacitorcalculator.components

enum class CapacitorTolerance(val percentage: String) {
    D("0.5%"),
    F("1%"),
    G("2%"),
    H("3%"),
    J("5%"),
    K("10%"),
    M("20%"),
    P("+100%/-0%"),
    Z("+80%/-20%");

    override fun toString(): String {
        return "${this.name}: ${this.percentage}"
    }
}
