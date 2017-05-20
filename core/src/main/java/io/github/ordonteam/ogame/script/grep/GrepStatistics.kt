package io.github.ordonteam.ogame.script.grep

import com.google.gson.GsonBuilder
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.loopOnce
import java.io.File

object GrepStatistics {
    @JvmStatic
    fun main(args: Array<String>) {
        loopOnce {
            val sin = loginAndGetSin()
            get("http://uni9.ogam.net.pl/index.php?page=stats&sin=$sin")
            val points = grepPointsStatistic()
            changeToFleet()
            val fleets = grepPointsStatistic()
            save(points, fleets)
        }
    }

    private fun save(points: List<PlayerPoints>, fleets: List<PlayerPoints>) {
        File("ogam/points.json").writeText(GsonBuilder().setPrettyPrinting().create().toJson(points))
        File("ogam/fleets.json").writeText(GsonBuilder().setPrettyPrinting().create().toJson(fleets))
    }
}