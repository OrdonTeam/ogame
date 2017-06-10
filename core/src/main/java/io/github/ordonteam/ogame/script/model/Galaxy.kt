package io.github.ordonteam.ogame.script.model

data class Universe(
        val galaxies: Map<Int, Galaxy>) {
    fun putPlanet(planet: Planet): Universe {
        val galaxyId = planet.position.galaxy
        val galaxy = galaxies[galaxyId] ?: Galaxy(emptyMap())
        return copy(galaxies = galaxies.toMutableMap().apply { put(galaxyId, galaxy.putPlanet(planet)) })
    }
}

data class Galaxy(
        val systems: Map<Int, System>) {
    fun putPlanet(planet: Planet): Galaxy {
        val systemId = planet.position.system
        val system = systems[systemId] ?: System(emptyMap())
        return copy(systems = systems.toMutableMap().apply { put(systemId, system.putPlanet(planet)) })
    }
}

data class System(
        val planets: Map<Int, Planet>) {
    fun putPlanet(planet: Planet): System {
        return copy(planets = planets.toMutableMap().apply { put(planet.position.planet, planet) })
    }
}

data class Planet(
        val position: Position,
        val playerId: PlayerId,
        val isIdle: Value<Status>,
        val moonSize: Value<Int>?)

data class Position(val galaxy: Int, val system: Int, val planet: Int, val typePlanet: PlanetType = PlanetType.Planet)

enum class PlanetType(val type: String) {
    Planet("1"),
    Moon("3")
}