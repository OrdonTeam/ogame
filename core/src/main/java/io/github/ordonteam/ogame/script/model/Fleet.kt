package io.github.ordonteam.ogame.script.model

data class Fleet(val ships: Map<Ship, Long>) {
    val hasShips = ships.isNotEmpty()
}

enum class Ship(val id: String) {
    EXPANSION_PROBE("210"),
    ULTRA_TRANSPORTER("217")
}

data class Defence(val utilities: Map<Utility, Long>) {
    val hasUtilities = utilities.isNotEmpty()
}

enum class Utility(val text: String) {
    ROCKET_LAUNCHER("Wyrzutnia rakiet"),
    LIGHT_LASER("Lekkie działo laserowe"),
    HEAVY_LASER("Ciężkie działo laserowe"),
    GAUSS_CANNON("Działo Gaussa"),
    ION_CANNON("Działo jonowe"),
    PLASMA_TURRET("Wyrzutnia plazmy"),
    SMALL_SHIELD("Mała powłoka ochronna"),
    LARGE_SHIELD("Duża powłoka ochronna"),
    ANTI_BALLISTIC_MISSILE("Przeciwrakieta"),
    BALLISTIC_MISSILE("Rakieta międzyplanetarna");

    companion object {
        fun fromText(text: String): Utility {
            return Utility.values().find { it.text == text } ?: throw RuntimeException("Not parsable utility $text")
        }
    }
}
