package io.github.ordonteam.ogame.script

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.ordonteam.ogame.script.core.read
import io.github.ordonteam.ogame.script.model.Position
import io.github.ordonteam.ogame.script.model.SpyReport
import io.github.ordonteam.ogame.script.model.Value
import java.io.File
import java.lang.reflect.Type

object Search {
    @JvmStatic
    fun main(args: Array<String>) {
        searchReports()
    }
}

private fun searchReports() {
    val reports = File("ogam/reports").listFiles().map {
        it.name.take(it.name.indexOf(".")) to read<SpyReport>(it)
    }
    reports
            .filter { it.second.defence.utilities.values.sum() > 0L }
            .filter { it.second.fleet.ships.values.sum() > 1_000_000L }
            .sortedBy { it.second.fleet.ships.values.sum() / it.second.defence.utilities.values.sum()  }
            .forEach { println(it) }
}

private fun searchPlanets() {
    val planets = File("ogam/planets").listFiles().flatMap {
        val json: String = it.readText()
        val type: Type = object : TypeToken<List<Planet>>() {}.type
        Gson().fromJson<List<Planet>>(json, type)
    }
    planets
            .groupBy { it.playerId }["2863"]!!
            .forEach { println(it.position) }
//    loopOnce {
//        val sin = loginAndGetSin()
//        planets
//                .filter { it.playerId == "5654" }
//                .forEach {
//                    generateNewSpyReport(sin, it.position.galaxy, it.position.system, it.position.planet)
//                }
//    }
}

data class Planet(
        val position: Position,
        val playerId: String,
        val isIdle: Value<Boolean>,
        val moonSize: Value<Int>?)