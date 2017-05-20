package io.github.ordonteam.ogame.script.model

data class Fleet(val ships: Map<Ship, Long>) {
    val hasShips = ships.isEmpty()
}

enum class Ship(val id: String) {
    EXPANSION_PROBE("210"),
    ULTRA_TRANSPORTER("217")
}
