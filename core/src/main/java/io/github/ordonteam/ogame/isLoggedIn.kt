package io.github.ordonteam.ogame

import org.openqa.selenium.phantomjs.PhantomJSDriver

fun isLoggedIn(webDriver: PhantomJSDriver, login: String): Boolean {
    webDriver.get("https://s146-pl.ogame.gameforge.com/game/index.php?page=overview")
    return webDriver.findElementById("playerName").text.contains(login)
}