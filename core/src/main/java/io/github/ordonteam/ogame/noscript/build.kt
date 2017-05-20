package io.github.ordonteam.ogame.noscript

fun build(webDriver: org.openqa.selenium.WebDriver, building: io.github.ordonteam.ogame.noscript.Building) {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=${building.page}")
    webDriver.findElementById(building.button)
            .findElement(org.openqa.selenium.By.tagName("div"))
            .findElement(org.openqa.selenium.By.tagName("div"))
            .findElements(org.openqa.selenium.By.tagName("a"))[0]
            .click()
}

enum class Building(val page: String, val button: String) {
    METAL("resources", "button1"),
    CRYSTAL("resources", "button2"),
    DEUTERIUM("resources", "button3"),
    POWER_PLANT("resources", "button4"),
    LABORATORY("station", "button2")
}