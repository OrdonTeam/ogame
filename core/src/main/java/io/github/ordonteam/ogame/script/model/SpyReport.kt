package io.github.ordonteam.ogame.script.model

data class SpyReport(val metal: Long, val crystal: Long, val deuterium: Long, val fleet: Fleet) {
    val resources = metal + crystal + deuterium
    val resourcesValue = metal + 2 * crystal + 4 * deuterium

    fun afterAttack() = copy(metal = metal / 2, crystal = crystal / 2, deuterium = deuterium / 2)
}