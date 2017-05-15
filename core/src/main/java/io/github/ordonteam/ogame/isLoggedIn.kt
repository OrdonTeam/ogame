package io.github.ordonteam.ogame

import org.openqa.selenium.WebDriver

fun isLoggedIn(webDriver: WebDriver): Boolean {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=overview")
    return webDriver.hasElementWithId("playerName")
}
