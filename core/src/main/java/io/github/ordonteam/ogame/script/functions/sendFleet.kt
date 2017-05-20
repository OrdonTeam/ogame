package io.github.ordonteam.ogame.script.functions

import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.sendFleet(sin: String, galaxy: Int, system: Int, planet: Int) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=$galaxy&system=$system&planet=$planet&planettype=1&target_mission=1")
    findElementById("217").sendKeys("500000")
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}