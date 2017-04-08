package io.github.ordonteam.ogame

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun build(webDriver: WebDriver, building: Building) {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=${building.page}")
    webDriver.findElementById(building.button)
            .findElement(By.tagName("div"))
            .findElement(By.tagName("div"))
            .findElements(By.tagName("a"))[0]
            .click()
}

enum class Building(val page: String, val button: String) {
    METAL("resources", "button1"),
    CRYSTAL("resources", "button2"),
    DEUTERIUM("resources", "button3"),
    POWER_PLANT("resources", "button4"),
    LABORATORY("station", "button2")
}