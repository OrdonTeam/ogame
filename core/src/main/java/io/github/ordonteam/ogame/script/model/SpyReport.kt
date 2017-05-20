package io.github.ordonteam.ogame.script.model

data class SpyReport(val metal: Long, val crystal: Long, val deuterium: Long, val fleet: Fleet) {
    val resources = metal + crystal + deuterium
    val resourcesValue = metal + 2 * crystal + 4 * deuterium
}