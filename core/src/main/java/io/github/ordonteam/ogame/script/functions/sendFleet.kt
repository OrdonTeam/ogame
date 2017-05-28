package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.model.Fleet
import io.github.ordonteam.ogame.script.model.Position
import org.openqa.selenium.remote.RemoteWebDriver

private val TIME_TO_FIRST_RETURN = "#time_0 > font"

fun RemoteWebDriver.sendFleet(sin: String, target: Position, fleet: Fleet, mission: Mission) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport" +
            "&galaxy=${target.galaxy}" +
            "&system=${target.system}" +
            "&planet=${target.planet}" +
            "&planettype=${target.typePlanet.type}" +
            "&target_mission=${mission.type}")
    if (findElementsByClassName("planet").isEmpty()) {
        waitForReturn()
        sendFleet(sin, target, fleet, mission)
    } else {
        fleet.ships.forEach { ship, count ->
            findElementById(ship.id).sendKeys(count.toString())
        }
        findElementByClassName("planet").click()
        findElementByXPath("//input[@type='submit']").click()
        findElementByXPath("//input[@type='submit']").click()
    }
}

private fun RemoteWebDriver.waitForReturn() {
    val timeToReturn = findElementByCssSelector(TIME_TO_FIRST_RETURN).text
    val minutes = timeToReturn.split(" ")[1].dropLast(1).toLong()
    val seconds = timeToReturn.split(" ")[2].dropLast(1).toLong()
    val millis = ((minutes * 60) + seconds + 1) * 1000
    System.err.println("ALL SLOTS IN USE -- WAITING ${millis / 1000L}")
    Thread.sleep(millis)
}

enum class Mission(val type: String) {
    ATTACK("1"),
    SPY("6")
}
