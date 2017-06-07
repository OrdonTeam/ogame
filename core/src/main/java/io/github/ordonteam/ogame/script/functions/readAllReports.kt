package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.model.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

private val SPY_MESSAGES = "body > center > center > table:nth-child(2) > tbody > tr:nth-child(4) > th:nth-child(1) > a > font"
private val ALL_MESSAGE_TABLE = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody"
private val DELETE_MESSAGE = "th:nth-child(2) > input[type=\"checkbox\"]"
private val REPORT_TITLE = "td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(1) > td > a"
private val METAL_IN_REPORT = "td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(2)"
private val CRYSTAL_IN_REPORT = "td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(5)"
private val DEUTERIUM_IN_REPORT = "td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(3) > td:nth-child(2)"
private val FLEET_TABLE = "td:nth-child(2) > table:nth-child(2) > tbody"
private val DEFENCE_TABLE = "td:nth-child(2) > table:nth-child(3) > tbody"
private val REMOVE_SELECTED = "body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1) > th > input[type=\"submit\"]"

fun RemoteWebDriver.readAllReports(sin: String): List<Pair<Position, SpyReport>> {
    get("http://uni9.ogam.net.pl/index.php?page=messages&sin=$sin")
    findElementByCssSelector(SPY_MESSAGES).click()
    val messages = findElementByCssSelector(ALL_MESSAGE_TABLE).findElements(By.tagName("tr")).drop(3).dropLast(2)
    val reports = readRecursively(messages)
    findElementByCssSelector(REMOVE_SELECTED).click()
    return reports
}

private fun readRecursively(messages: List<WebElement>): List<Pair<Position, SpyReport>> {
    if (messages.isEmpty()) {
        return emptyList()
    } else {
        return readPositionAndReport(messages[0], messages[1]) //+ readRecursively(messages.drop(2))
    }
}

private fun readPositionAndReport(header: WebElement, body: WebElement): List<Pair<Position, SpyReport>> {
    if (isReport(body)) {
        deleteReport(header)
        return listOf(readPosition(body) to readReport(body))
    } else {
        return emptyList()
    }
}

private fun isReport(body: WebElement): Boolean {
    return body.findElements(By.cssSelector(REPORT_TITLE)).isNotEmpty()
}

private fun deleteReport(header: WebElement) {
    header.findElement(By.cssSelector(DELETE_MESSAGE)).click()
}

private fun readPosition(body: WebElement): Position {
    val title = body.findElement(By.cssSelector(REPORT_TITLE)).text
    val position = title.drop(title.indexOf("[") + 1).takeWhile { it != ']' }.split(":").map { it.toInt() }
    return Position(position[0], position[1], position[2])
}

private fun readReport(body: WebElement): SpyReport {
    return SpyReport(
            metal = readMetal(body),
            crystal = readCrystal(body),
            deuterium = readDeuterium(body),
            fleet = readFleet(body),
            defence = readDefence(body))
}

fun readMetal(body: WebElement) = body.findElement(By.cssSelector(METAL_IN_REPORT)).toLongWithoutDots()
fun readCrystal(body: WebElement) = body.findElement(By.cssSelector(CRYSTAL_IN_REPORT)).toLongWithoutDots()
fun readDeuterium(body: WebElement) = body.findElement(By.cssSelector(DEUTERIUM_IN_REPORT)).toLongWithoutDots()


private fun readFleet(body: WebElement): Fleet {
    val fleetTable = body.findElement(By.cssSelector(FLEET_TABLE))
    val hasShips = fleetTable.findElements(By.tagName("tr")).size != 1
    if (hasShips) {
        val kind = fleetTable
                .findElements(By.xpath(".//td[@align='left']"))
                .map { it.toShip() }
        val count = fleetTable
                .findElements(By.xpath(".//td[@align='right']"))
                .map { it.toLongWithoutDots() }
        return Fleet((0..kind.size - 1).map { kind[it] to count[it] }.toMap())
    } else {
        return Fleet(emptyMap())
    }
}

private fun WebElement.toShip() = Ship.fromText(text)

private fun readDefence(body: WebElement): Defence {
    val defenceTable = body.findElement(By.cssSelector(DEFENCE_TABLE))
    val hasUtilities = defenceTable.findElements(By.tagName("tr")).size != 1
    if (hasUtilities) {
        val kind = defenceTable
                .findElements(By.xpath(".//td[@align='left']"))
                .map { it.toUtility() }
        val count = defenceTable
                .findElements(By.xpath(".//td[@align='right']"))
                .map { it.toLongWithoutDots() }
        return Defence((0..kind.size - 1).map { kind[it] to count[it] }.toMap())
    } else {
        return Defence(emptyMap())
    }
}

private fun WebElement.toUtility() = Utility.fromText(text)

private fun WebElement.toLongWithoutDots() = text.replace(".", "").toLong()
