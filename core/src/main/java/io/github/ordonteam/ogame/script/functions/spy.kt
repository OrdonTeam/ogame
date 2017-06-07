package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.model.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

private val SPY_MESSAGES = "body > center > center > table:nth-child(2) > tbody > tr:nth-child(4) > th:nth-child(1) > a > font"
private val REPORT_TITLE = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(1) > td"

fun RemoteWebDriver.generateNewSpyReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
    spy(sin, Position(galaxy, system, planet))
    waitForReport(sin, galaxy, system, planet)
    return readReport(sin, Position(galaxy, system, planet))
}

private fun RemoteWebDriver.spy(sin: String, position: Position) {
    sendFleet(sin, position, Fleet(mapOf(Ship.EXPANSION_PROBE to 300L)), Mission.SPY)
}

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

fun RemoteWebDriver.readReport(sin: String, position: Position): SpyReport {
    return readAllReports(sin).first { it.first == position }.second
}
