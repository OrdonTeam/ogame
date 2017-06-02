package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.functions.recoverFromAntybot
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import java.util.logging.Level
import java.util.logging.Logger

fun loopForEver(block: RemoteWebDriver.() -> Unit) {
    while (true) {
        try {
            loopOnce(block)
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun loopOnce(block: RemoteWebDriver.() -> Unit) {
    val driver = RemoteWebDriver(URL("http://localhost:9515"), DesiredCapabilities.chrome()).apply {
        Logger.getLogger(RemoteWebDriver::class.java.name).level = Level.OFF
    }
    try {
        driver.block()
    } catch (e: Exception) {
        e.printStackTrace()
        driver.recoverFromAntybot()
        Thread.sleep(60 * 1000L)
    } finally {
        driver.close()
    }
}