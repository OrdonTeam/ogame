package io.github.ordonteam.ogame.script.model

data class Fleet(val ships: Map<Ship, Long>) {
    val hasShips = ships.isNotEmpty()
    val isEasilyBeatable = ships
            .filterNot { it.key == Ship.FLEET_SLOV }
            .filterNot { it.key == Ship.EXPANSION_PROBE }
            .filterNot { it.key == Ship.SOLAR_SATELLITE }
            .map { it.value }
            .sum() == 0L
}

enum class Ship(val id: String, val text: String) {
    SMALL_CARGO("xxx", "Mały transporter"),
    LARGE_CARGO("xxx", "Duży transporter"),
    LIGHT_FIGHTER("xxx", "Lekki myśliwiec"),
    HEAVY_FIGHTER("xxx", "Ciężki myśliwiec"),
    CRUISER("xxx", "Krążownik"),
    BATTLE_SHIP("xxx", "Okręt wojenny"),
    COLONY_SHIP("xxx", "Statek kolonizacyjny"),
    RECYCLER("xxx", "Recykler"),
    EXPANSION_PROBE("210", "Sonda szpiegowska"),
    BOMBER("xxx", "Bombowiec"),
    SOLAR_SATELLITE("xxx", "Satelita słoneczny"),
    DESTROYER("xxx", "Niszczyciel"),
    DEATH_STAR("xxx", "Gwiazda Śmierci"),
    BATTLE_CRUISER("xxx", "Pancernik"),
    FLEET_SLOV("xxx", "Fleet Slov"),
    ULTRA_TRANSPORTER("217", "Ultra transporter"),
    AURORA("xxx", "Aurora"),
    SZPERACZ("xxx", "Szperacz");

    companion object {
        fun fromText(text: String): Ship {
            return Ship.values().find { it.text == text } ?: throw RuntimeException("Not parsable ship $text")
        }
    }
}

data class Defence(val utilities: Map<Utility, Long>) {
    val hasUtilities = utilities.isNotEmpty()
    val isEasilyBeatable = utilities
            .filterNot { it.key == Utility.SMALL_SHIELD }
            .filterNot { it.key == Utility.LARGE_SHIELD }
            .filterNot { it.key == Utility.ANTI_BALLISTIC_MISSILE }
            .filterNot { it.key == Utility.BALLISTIC_MISSILE }
            .map { it.value }
            .sum() == 0L
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
