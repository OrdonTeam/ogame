package io.github.ordonteam.ogame.script.core

import io.github.ordonteam.ogame.script.model.Defence
import io.github.ordonteam.ogame.script.model.Fleet

fun simulate(attackingFleet: Fleet, defendingFleet: Fleet, defendingDefence: Defence): Boolean {
    return defendingFleet.isEasilyBeatable && defendingDefence.isEasilyBeatable
}