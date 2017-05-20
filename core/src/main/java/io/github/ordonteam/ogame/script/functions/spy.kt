package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.model.Fleet
import io.github.ordonteam.ogame.script.model.SpyReport
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

private val SPY_MESSAGES = "body > center > center > table:nth-child(2) > tbody > tr:nth-child(4) > th:nth-child(1) > a > font"
private val REPORT_TITLE = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(1) > td"
private val METAL_IN_REPORT = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(2)"
private val CRYSTAL_IN_REPORT = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(5)"
private val DEUTERIUM_IN_REPORT = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(3) > td:nth-child(2)"
private val FLEET_TABLE = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(2) > tbody"

fun RemoteWebDriver.generateNewSpyReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
    spy(sin, galaxy, system, planet)
    val report = readReport(sin, galaxy, system, planet)
    return report
}

private fun RemoteWebDriver.spy(sin: String, galaxy: Int, system: Int, planet: Int) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=$galaxy&system=$system&planet=$planet&planettype=1&target_mission=6")
    findElementById("210").sendKeys("1000")
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}

private fun RemoteWebDriver.readReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
    return doReadReport(sin, galaxy, system, planet)
}

private fun RemoteWebDriver.doReadReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
    waitForReport(sin, galaxy, system, planet)
    val result = SpyReport(
            metal = findElementByCssSelector(METAL_IN_REPORT).toLongWithoutDots(),
            crystal = findElementByCssSelector(CRYSTAL_IN_REPORT).toLongWithoutDots(),
            deuterium = findElementByCssSelector(DEUTERIUM_IN_REPORT).toLongWithoutDots(),
            fleet = readFleet())
    findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > th:nth-child(2) > input[type=\"checkbox\"]").click()
    findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1) > th > input[type=\"submit\"]").click()
    return result
}

private fun RemoteWebDriver.readFleet(): Fleet {
    return Fleet(
            isEmpty = findElementByCssSelector(FLEET_TABLE).findElements(By.tagName("tr")).size == 1)
}

private fun WebElement.toLongWithoutDots() = text.replace(".", "").toLong()

private fun RemoteWebDriver.waitForReport(sin: String, galaxy: Int, system: Int, planet: Int) {
    (0..10).forEach {
        if (isReportAvailable(sin, galaxy, system, planet))
            return
        else
            Thread.sleep(1000)
    }
    throw RuntimeException("Spy report await timeout. [$galaxy:$system:$planet]")
}

private fun RemoteWebDriver.isReportAvailable(sin: String, galaxy: Int, system: Int, planet: Int): Boolean {
    get("http://uni9.ogam.net.pl/index.php?page=messages&sin=$sin")
    findElementByCssSelector(SPY_MESSAGES).click()
    val report = findElementsByCssSelector(REPORT_TITLE).firstOrNull()
    if (report == null) {
        return false
    } else {
        return report.text.contains("[$galaxy:$system:$planet]")
    }
}

