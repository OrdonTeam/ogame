package io.github.ordonteam.ogame.script.grep

import com.google.gson.GsonBuilder
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.loopOnce
import io.github.ordonteam.ogame.utils.wait
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import java.io.File

object GrepGalaxy {
    @JvmStatic
    fun main(args: Array<String>) {
        loopOnce {
            val sin = loginAndGetSin()
            (1..11).forEach { galaxy ->
                (1..499).forEach { system ->
                    if (!File("ogam/planets$galaxy-$system.json").exists()) {
                        saveOne(galaxy, system, readPlanetsFromPage(sin, galaxy, system))
                    }
                }
            }
        }
    }
}

private fun RemoteWebDriver.readPlanetsFromPage(sin: String, galaxy: Int, system: Int): List<Planet> {
    get("http://uni9.ogam.net.pl/index.php?page=galaxy&sin=$sin&galaxy=$galaxy&system=$system")
    wait(3, { findElementsByCssSelector("#loadgalaxy > table").size > 0 })
    val planets = findElementByCssSelector("#loadgalaxy > table > tbody")
            .findElements(By.tagName("tr"))
            .drop(1)
            .take(15)
    println(planets.size)
    return planets.mapIndexed { position, planet ->
        val isPlanet = planet.findElements(By.tagName("td"))[1].findElements(By.tagName("a")).isNotEmpty()
        if (isPlanet) {
            val hasPlayer = planet.findElements(By.tagName("td"))[5].findElements(By.tagName("a")).isNotEmpty()
            if (hasPlayer) {
                val linkWithId = planet.findElements(By.tagName("td"))[5].findElement(By.tagName("a")).getAttribute("onmouseover")
                val idAndSuffix = linkWithId.drop(linkWithId.indexOf("write&id=") + 9)
                val id = idAndSuffix.take(idAndSuffix.indexOf("&"))
                val hasMoon = planet.findElements(By.tagName("td"))[3].findElements(By.tagName("a")).isNotEmpty()
                Planet(galaxy, system, position + 1, id, hasMoon)
            } else {
                Planet(galaxy, system, position + 1, null, null)
            }
        } else {
            null
        }
    }.filterNotNull()
}

data class Planet(val galaxy: Int, val system: Int, val position: Int, val id: String?, val hasMoon: Boolean?)

private fun saveOne(galaxy: Int, system: Int, planets: List<Planet>): List<Planet> {
    File("ogam/planets$galaxy-$system.json").writeText(GsonBuilder().setPrettyPrinting().create().toJson(planets))
    return planets
}
