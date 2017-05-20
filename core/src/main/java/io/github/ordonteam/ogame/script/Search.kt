package io.github.ordonteam.ogame.script

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.ordonteam.ogame.script.grep.Planet
import io.github.ordonteam.ogame.script.grep.PlayerPoints
import java.io.File
import java.lang.reflect.Type
import java.util.*

object Search {
    @JvmStatic
    fun main(args: Array<String>) {
        search()
    }

}

private fun search() {
    val planets = File("ogam/pla").listFiles().flatMap {
        val json: String = it.readText()
        val type: Type = object : TypeToken<List<Planet>>() {}.type
        Gson().fromJson<List<Planet>>(json, type)
    }
    val points = File("ogam/fleets.json").let {
        val json: String = it.readText()
        val type: Type = object : TypeToken<List<PlayerPoints>>() {}.type
        Gson().fromJson<List<PlayerPoints>>(json, type)
    }
    points
            .filter { it.id == "5506" }
//            .filter { it.points < 20_000_000_000}
//            .filter { it.points > 1_000_000_000}
            .forEach { player ->
                val playersPlanets = planets.filter { it.id == player.id }
                val moonsCount = playersPlanets.count { it.hasMoon ?: false }
                if (moonsCount < 20) {
                    println("$moonsCount ${playersPlanets.size} ${player.points}")
                    playersPlanets.forEach(::println)
                    println()
                }
            }
}
