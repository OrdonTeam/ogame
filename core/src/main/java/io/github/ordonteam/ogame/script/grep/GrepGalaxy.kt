package io.github.ordonteam.ogame.script.grep

import com.google.gson.GsonBuilder
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.loopOnce
import io.github.ordonteam.ogame.script.model.Planet
import io.github.ordonteam.ogame.script.model.Position
import io.github.ordonteam.ogame.script.model.Status
import io.github.ordonteam.ogame.script.model.Value
import io.github.ordonteam.ogame.utils.wait
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
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

fun RemoteWebDriver.readPlanetsFromPage(sin: String, galaxy: Int, system: Int): List<Planet> {
    get("http://uni9.ogam.net.pl/index.php?page=galaxy&sin=$sin&galaxy=$galaxy&system=$system")
    wait(3, { findElementsByCssSelector("#loadgalaxy > table").size > 0 })
    val planets = findElementByCssSelector("#loadgalaxy > table > tbody")
            .findElements(By.tagName("tr"))
            .drop(1)
            .take(15)
    System.err.println(planets.size)
    return planets.mapIndexed { index, planet ->
        if (!isEmpty(planet)) {
            listOf(Planet(
                    position = Position(galaxy, system, index + 1),
                    playerId = getPlayerId(planet),
                    isIdle = isIdle(planet),
                    moonSize = getMoonInfo(planet)))
        } else {
            emptyList()
        }
    }.flatten()
}

private fun isEmpty(planet: WebElement): Boolean {
    val isPlanet = planet.findElements(By.tagName("td"))[1].findElements(By.tagName("a")).isNotEmpty()
    val hasPlayer = planet.findElements(By.tagName("td"))[5].findElements(By.tagName("a")).isNotEmpty()
    return !(isPlanet && hasPlayer)
}

private fun getPlayerId(planet: WebElement): String {
    val linkWithId = planet.findElements(By.tagName("td"))[5].findElement(By.tagName("a")).getAttribute("onmouseover")
    val idAndSuffix = linkWithId.drop(linkWithId.indexOf("write&id=") + 9)
    return idAndSuffix.take(idAndSuffix.indexOf("&"))
}

fun isIdle(planet: WebElement): Value<Status> {
    if(planet.findElements(By.className("vacation")).isNotEmpty())
        return Value(Status.BANNED)
    if(planet.findElements(By.className("inactive")).isNotEmpty())
        return Value(Status.INACTIVE)
    return Value(Status.ACTIVE)
}

private fun getMoonInfo(planet: WebElement): Value<Int>? {
    val hasMoon = planet.findElements(By.tagName("td"))[3].findElements(By.tagName("a")).isNotEmpty()
    return if (hasMoon) Value(3000) else null
}

private fun saveOne(galaxy: Int, system: Int, planets: List<Planet>) {
    File("ogam/planets$galaxy-$system.json").writeText(GsonBuilder().setPrettyPrinting().create().toJson(planets))
}
