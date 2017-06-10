package io.github.ordonteam.ogame

import io.github.ordonteam.ogame.script.functions.Mission
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.functions.readAllReports
import io.github.ordonteam.ogame.script.functions.sendFleet
import io.github.ordonteam.ogame.script.loopOnce
import io.github.ordonteam.ogame.script.model.Fleet
import io.github.ordonteam.ogame.script.model.PlanetType.Moon
import io.github.ordonteam.ogame.script.model.PlanetType.Planet
import io.github.ordonteam.ogame.script.model.Position
import io.github.ordonteam.ogame.script.model.Ship

private val positions = listOf(
//        Position(galaxy = 1, system = 276, planet = 6, typePlanet = Planet),
//        Position(galaxy = 1, system = 276, planet = 6, typePlanet = Moon),
//        Position(galaxy = 1, system = 276, planet = 10, typePlanet = Planet),
//        Position(galaxy = 1, system = 276, planet = 10, typePlanet = Moon),
//        Position(galaxy = 1, system = 428, planet = 9, typePlanet = Planet),
//        Position(galaxy = 1, system = 428, planet = 9, typePlanet = Moon),
//        Position(galaxy = 2, system = 125, planet = 9, typePlanet = Planet),
//        Position(galaxy = 2, system = 125, planet = 9, typePlanet = Moon),
//        Position(galaxy = 2, system = 378, planet = 10, typePlanet = Planet),
//        Position(galaxy = 2, system = 378, planet = 10, typePlanet = Moon),
//        Position(galaxy = 3, system = 290, planet = 7, typePlanet = Planet),
//        Position(galaxy = 3, system = 290, planet = 7, typePlanet = Moon),
//        Position(galaxy = 3, system = 74, planet = 7, typePlanet = Planet),
//        Position(galaxy = 3, system = 74, planet = 7, typePlanet = Moon),
//        Position(galaxy = 4, system = 277, planet = 6, typePlanet = Planet),
//        Position(galaxy = 4, system = 277, planet = 6, typePlanet = Moon),
//        Position(galaxy = 5, system = 288, planet = 8, typePlanet = Planet),
//        Position(galaxy = 5, system = 288, planet = 8, typePlanet = Moon),
//        Position(galaxy = 6, system = 117, planet = 9, typePlanet = Planet),
//        Position(galaxy = 6, system = 117, planet = 9, typePlanet = Moon),
//        Position(galaxy = 6, system = 382, planet = 10, typePlanet = Planet),
//        Position(galaxy = 6, system = 382, planet = 10, typePlanet = Moon),
//        Position(galaxy = 7, system = 365, planet = 10, typePlanet = Planet),
//        Position(galaxy = 7, system = 365, planet = 10, typePlanet = Moon),
//        Position(galaxy = 7, system = 54, planet = 7, typePlanet = Planet),
//        Position(galaxy = 7, system = 54, planet = 7, typePlanet = Moon),
//        Position(galaxy = 8, system = 121, planet = 9, typePlanet = Planet),
//        Position(galaxy = 8, system = 121, planet = 9, typePlanet = Moon),
//        Position(galaxy = 8, system = 379, planet = 10, typePlanet = Planet),
//        Position(galaxy = 8, system = 379, planet = 10, typePlanet = Moon),
//        Position(galaxy = 9, system = 151, planet = 10, typePlanet = Planet),
//        Position(galaxy = 9, system = 151, planet = 10, typePlanet = Moon),
//        Position(galaxy = 9, system = 496, planet = 6, typePlanet = Planet),
//        Position(galaxy = 9, system = 496, planet = 6, typePlanet = Moon),
        Position(galaxy = 10, system = 123, planet = 10, typePlanet = Planet),
        Position(galaxy = 10, system = 123, planet = 10, typePlanet = Moon),
        Position(galaxy = 10, system = 383, planet = 10, typePlanet = Planet),
        Position(galaxy = 10, system = 383, planet = 10, typePlanet = Moon),
        Position(galaxy = 11, system = 160, planet = 10, typePlanet = Planet),
        Position(galaxy = 11, system = 160, planet = 10, typePlanet = Moon),
        Position(galaxy = 11, system = 498, planet = 6, typePlanet = Planet),
        Position(galaxy = 11, system = 498, planet = 6, typePlanet = Moon)
)


fun main(args: Array<String>) {
    loopOnce {
        val sin = loginAndGetSin()
        positions.forEach { position ->
            sendFleet(sin, position, Fleet(mapOf(Ship.EXPANSION_PROBE to 99L)), Mission.SPY, "1")
            Thread.sleep(1000L)
        }
    }
}