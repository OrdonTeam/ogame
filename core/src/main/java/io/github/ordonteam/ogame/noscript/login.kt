package io.github.ordonteam.ogame.noscript

fun login(webDriver: org.openqa.selenium.WebDriver, login: String, password: String, server: String) {
    webDriver.get("https://pl.ogame.gameforge.com/")
    webDriver.findElementById("loginBtn").click()
    webDriver.findElementById("usernameLogin").sendKeys(login)
    webDriver.findElementById("passwordLogin").sendKeys(password)
    webDriver.findElementById("serverLogin").sendKeys(server)
    webDriver.findElementById("loginSubmit").click()
}
