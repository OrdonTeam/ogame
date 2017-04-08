package io.github.ordonteam.ogame

import org.openqa.selenium.WebDriver

fun login(webDriver: WebDriver, login: String, password: String, server: String) {
    webDriver.get("https://pl.ogame.gameforge.com/")
    webDriver.findElementById("loginBtn").click()
    webDriver.findElementById("usernameLogin").sendKeys(login)
    webDriver.findElementById("passwordLogin").sendKeys(password)
    webDriver.findElementById("serverLogin").sendKeys(server)
    webDriver.findElementById("loginSubmit").click()
}
