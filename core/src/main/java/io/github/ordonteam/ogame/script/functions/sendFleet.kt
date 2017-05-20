package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.model.Fleet
import io.github.ordonteam.ogame.script.model.Position
import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.sendFleet(sin: String, target: Position, fleet: Fleet) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport" +
            "&galaxy=${target.galaxy}" +
            "&system=${target.system}" +
            "&planet=${target.planet}" +
            "&planettype=${target.typePlanet.type}" +
            "&target_mission=1")
    fleet.ships.forEach { ship, count ->
        findElementById(ship.id).sendKeys(count.toString())
    }
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}