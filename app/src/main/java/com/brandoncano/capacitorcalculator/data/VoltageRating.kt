package com.brandoncano.capacitorcalculator.data

enum class VoltageRating(val code: String, val voltage: String) {
    G0("0G", "4V"),
    L0("0L", "5.5V"),
    J0("0J", "6.3V"),
    A1("1A", "10V"),
    C1("1C", "16V"),
    E1("1E", "25V"),
    H1("1H", "50V"),
    J1("1J", "63V"),
    K1("1K", "80V"),
    A2("2A", "100V"),
    Q2("2Q", "110V"),
    B2("2B", "125V"),
    C2("2C", "160V"),
    Z2("2Z", "180V"),
    D2("2D", "200V"),
    P2("2P", "220V"),
    E2("2E", "250V"),
    F2("2F", "315V"),
    V2("2V", "350V"),
    G2("2G", "400V"),
    W2("2W", "450V"),
    H2("2H", "500V"),
    J2("2J", "630V"),
    A3("3A", "1000V");

    companion object {
        fun getCodeList(): List<String> {
            return entries.map { it.code }
        }

        fun getVoltageList(): List<String> {
            return entries.map { it.voltage }
        }

        fun getVoltageValue(code: String): String {
            return entries.find { it.code == code }?.voltage ?: ""
        }
    }
}
