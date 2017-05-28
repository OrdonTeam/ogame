package io.github.ordonteam.ogame.script.model

data class Fleet(val ships: Map<Ship, Long>) {
    val hasShips = ships.isNotEmpty()
}

enum class Ship(val id: String) {
    EXPANSION_PROBE("210"),
    ULTRA_TRANSPORTER("217")
}

data class Defence(val utilities: Map<Utility, Long>){
    val hasUtilities = utilities.isNotEmpty()
}

enum class Utility{
    ROCKET_LAUNCHER
}
