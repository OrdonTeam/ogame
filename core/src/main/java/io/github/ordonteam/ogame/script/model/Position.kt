package io.github.ordonteam.ogame.script.model

data class Position(val galaxy: Int, val system: Int, val planet: Int, val typePlanet: PlanetType = PlanetType.Planet)

enum class PlanetType(val type: String) {
    Planet("1")
}
