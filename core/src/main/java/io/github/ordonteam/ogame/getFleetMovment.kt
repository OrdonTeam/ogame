package io.github.ordonteam.ogame

import org.openqa.selenium.WebDriver

fun getFleetMovement(webDriver: WebDriver): List<FleetMovement> {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=overview")
    if (webDriver.findElementById("eventboxBlank").isDisplayed) {
        return emptyList()
    } else {
        webDriver.findElementById("js_eventDetailsClosed").click()
        webDriver.waitForElementWithClassName("eventFleet")
        return webDriver.findElementsByClassName("eventFleet").map { FleetMovement(it.hasElementWithClassName("friendly")) }
    }
}

data class FleetMovement(val friendly: Boolean)