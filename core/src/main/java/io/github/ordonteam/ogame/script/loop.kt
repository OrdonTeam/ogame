package io.github.ordonteam.ogame.script

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

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
    val driver = RemoteWebDriver(URL("http://localhost:9515"), DesiredCapabilities.chrome())
    try {
        driver.block()
    } catch (e: Exception) {
        e.printStackTrace()
        Thread.sleep(3 * 60 * 1000L)
    } finally {
        driver.close()
    }
}