package io.github.ordonteam.ogame.script.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import kotlin.system.measureTimeMillis

data class Game(
        val statistics: Statistics,
        val universe: Universe) {

    fun updatePlayerStatistic(playerId: String, updatePlayer: Player.() -> Player): Game {
        return copy(statistics = statistics.updatePlayer(playerId, updatePlayer))
    }

    fun addPlanet(planet: Planet): Game {
        return copy(universe = universe.putPlanet(planet))
    }

    companion object {
        fun read(): Game = Gson().fromJson<Game>(File("ogam/game.json").readText(), Game::class.java)
    }

    fun save() {
        File("ogam/game.json").writeText(GsonBuilder().setPrettyPrinting().create().toJson(this))
    }
}
