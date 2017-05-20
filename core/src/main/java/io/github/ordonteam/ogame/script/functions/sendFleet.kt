package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.model.Position
import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.sendFleet(sin: String, position: Position) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=${position.galaxy}&system=${position.system}&planet=${position.planet}&planettype=1&target_mission=1")
    findElementById("217").sendKeys("500000")
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}