package io.github.ordonteam.ogame.script.grep

import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.Select

fun RemoteWebDriver.grepPointsStatistic(): List<PlayerPoints> {
    return (0..7).flatMap {
        changeToPage(it)
        readPlayersFromPage()
    }
}

fun RemoteWebDriver.changeToFleet() {
    Select(findElementByCssSelector("body > center > center > form > table > tbody > tr:nth-child(2) > th > table > tbody > tr:nth-child(1) > th:nth-child(5) > select")).selectByIndex(1)
}

private fun RemoteWebDriver.changeToPage(page: Int) {
    Select(findElementByCssSelector("body > center > center > form > table > tbody > tr:nth-child(2) > th > table > tbody > tr:nth-child(1) > th:nth-child(7) > select")).selectByIndex(page)
}

private fun RemoteWebDriver.readPlayersFromPage(): List<PlayerPoints> {
    val players = findElementByCssSelector("body > center > center > table > tbody").let { table ->
        table.findElements(By.tagName("tr")).drop(1)
    }
    return players.map { player ->
        val playerName = player.findElements(By.tagName("th"))[2]
        val href = playerName.findElement(By.tagName("a")).getAttribute("href")
        PlayerPoints(
                href.drop(href.lastIndexOf("=") + 1),
                playerName.text,
                player.findElements(By.tagName("th"))[5].text.trim().replace(".", "").toLong()
        )
    }
}

data class PlayerPoints(val id: String, val name: String, val points: Long)