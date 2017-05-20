package io.github.ordonteam.ogame.noscript

fun getFleetMovement(webDriver: org.openqa.selenium.WebDriver): List<io.github.ordonteam.ogame.noscript.FleetMovement> {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=overview")
    if (webDriver.findElementById("eventboxBlank").isDisplayed) {
        return emptyList()
    } else {
        webDriver.findElementById("js_eventDetailsClosed").click()
        webDriver.waitForElementWithClassName("eventFleet")
        return webDriver.findElementsByClassName("eventFleet").map { io.github.ordonteam.ogame.noscript.FleetMovement(it.hasElementWithClassName("friendly")) }
    }
}

data class FleetMovement(val friendly: Boolean)