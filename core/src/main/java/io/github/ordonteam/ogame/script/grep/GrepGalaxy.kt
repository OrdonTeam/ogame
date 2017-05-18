package io.github.ordonteam.ogame.script.grep

import io.github.ordonteam.ogame.script.loginAndGetSin
import io.github.ordonteam.ogame.script.loopOnce

object GrepGalaxy {
    @JvmStatic
    fun main(args: Array<String>) {
        loopOnce {
            val sin = loginAndGetSin()
            get("http://uni9.ogam.net.pl/index.php?page=galaxy&sin=$sin")
            println(findElementById("loadgalaxy").getAttribute("innerHTML"))
//            val readPlanetsFromPage = readPlanetsFromPage(2, 373)
//            println("readPlanetsFromPage.size=" + readPlanetsFromPage.size)
//            readPlanetsFromPage.forEach {
//                println(it)
//            }
        }
    }
}

//
//private fun RemoteWebDriver.readPlanetsFromPage(galaxy: Int, system: Int): List<Planet> {
//    Thread.sleep(2000)
//    val planets = findElementsByXPath("//tr[onclick]")
//    println(planets.size)
//    return planets.mapIndexed { position, planet ->
//        val isPlanet = planet.findElements(By.tagName("th"))[1].findElements(By.tagName("a")).isNotEmpty()
//        if (isPlanet) {
//            Planet(galaxy, system, position - 1)
//        } else {
//            null
//        }
//    }.filterNotNull()
//}

data class Planet(val galaxy: Int, val system: Int, val position: Int)