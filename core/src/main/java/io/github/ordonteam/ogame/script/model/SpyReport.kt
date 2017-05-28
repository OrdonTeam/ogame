package io.github.ordonteam.ogame.script.model

data class SpyReport(val metal: Long, val crystal: Long, val deuterium: Long, val fleet: Fleet, val defence: Defence) {
    val resources = metal + crystal + deuterium
    val resourcesValue = metal + 2 * crystal + 4 * deuterium
    val isDefenceless = !fleet.hasShips && !defence.hasUtilities

    fun afterAttack() = copy(metal = metal / 2, crystal = crystal / 2, deuterium = deuterium / 2)
}