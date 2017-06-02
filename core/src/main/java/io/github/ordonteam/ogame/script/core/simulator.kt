package io.github.ordonteam.ogame.script.core

import io.github.ordonteam.ogame.script.model.Defence
import io.github.ordonteam.ogame.script.model.Fleet
import io.github.ordonteam.ogame.script.model.Ship
import io.github.ordonteam.ogame.script.model.Utility

fun simulate(attackingFleet: Fleet, defendingFleet: Fleet, defendingDefence: Defence): Boolean {
    return defendingFleet.howManyUltra() + defendingDefence.howManyUltra() < attackingFleet.ships.map { it.value }.sum()
}

private fun Defence.howManyUltra(): Long {
    return utilities.map { it.key.toUltra() * it.value }.sum()
}

private fun Fleet.howManyUltra(): Long {
    return ships.map { it.key.toUltra() * it.value }.sum()
}

private fun Ship.toUltra(): Long {
    return when (this) {
        Ship.SMALL_CARGO -> 4L
        Ship.LARGE_CARGO -> 13L
        Ship.LIGHT_FIGHTER -> 33L
        Ship.HEAVY_FIGHTER -> 128L
        Ship.CRUISER -> 256L
        Ship.BATTLE_SHIP -> 512L
        Ship.COLONY_SHIP -> 256L
        Ship.RECYCLER -> 64L
        Ship.EXPANSION_PROBE -> 16L
        Ship.BOMBER -> 1024L
        Ship.SOLAR_SATELLITE -> 32L
        Ship.DESTROYER -> 1024L
        Ship.DEATH_STAR -> 80_000L
        Ship.BATTLE_CRUISER -> 1024L
        Ship.FLEET_SLOV -> 128L
        Ship.ULTRA_TRANSPORTER -> 1024L
        Ship.AURORA -> 13_000L
        Ship.SZPERACZ -> 512L
    }
}

private fun Utility.toUltra(): Long {
    return when (this) {
        Utility.ROCKET_LAUNCHER -> 32L
        Utility.LIGHT_LASER -> 32L
        Utility.HEAVY_LASER -> 128L
        Utility.GAUSS_CANNON -> 512L
        Utility.ION_CANNON -> 256L
        Utility.PLASMA_TURRET -> 1024L
        Utility.SMALL_SHIELD -> 512L
        Utility.LARGE_SHIELD -> 1024L
        Utility.ANTI_BALLISTIC_MISSILE -> 0L
        Utility.BALLISTIC_MISSILE -> 0L
    }
}

