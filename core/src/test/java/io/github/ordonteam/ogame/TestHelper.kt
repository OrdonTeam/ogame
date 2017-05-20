package io.github.ordonteam.ogame

import org.openqa.selenium.WebDriver

val login: String = System.getenv("OGAME_LOGIN")

fun loginHelper(webDriver: WebDriver) {
    val password = System.getenv("OGAME_PASSWORD")
    val server = System.getenv("OGAME_SERVER")
    io.github.ordonteam.ogame.noscript.login(webDriver, login, password, server)
}