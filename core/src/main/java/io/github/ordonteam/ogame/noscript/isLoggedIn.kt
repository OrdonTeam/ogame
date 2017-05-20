package io.github.ordonteam.ogame.noscript

fun isLoggedIn(webDriver: org.openqa.selenium.WebDriver): Boolean {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=overview")
    return webDriver.hasElementWithId("playerName")
}
