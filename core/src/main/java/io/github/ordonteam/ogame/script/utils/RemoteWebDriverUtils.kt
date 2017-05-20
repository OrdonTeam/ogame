package io.github.ordonteam.ogame.script.utils

import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait

fun RemoteWebDriver.wait(timeOutInSeconds: Long, predicate: RemoteWebDriver.() -> Boolean) {
    WebDriverWait(this, timeOutInSeconds)
            .until({ predicate() })
}