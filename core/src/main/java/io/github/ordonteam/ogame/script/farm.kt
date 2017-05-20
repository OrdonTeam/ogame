package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.functions.generateNewSpyReport
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.functions.sendFleet
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.*

val targets = mutableListOf(
        Triple(2, 351, 7),
        Triple(2, 351, 6),
        Triple(2, 352, 7),
        Triple(2, 352, 1),
        Triple(2, 353, 10),
        Triple(2, 353, 9),
        Triple(2, 353, 7),
        Triple(2, 357, 6),
        Triple(2, 358, 6),
        Triple(2, 359, 7),
        Triple(2, 400, 8),
        Triple(2, 400, 7),
        Triple(2, 399, 7),
        Triple(2, 399, 8),
        Triple(2, 396, 7),
        Triple(2, 393, 9),
        Triple(2, 392, 7),
        Triple(2, 391, 9),
        Triple(2, 391, 8),
        Triple(2, 390, 7),
        Triple(2, 389, 7),
        Triple(2, 387, 4),
        Triple(2, 386, 12),
        Triple(2, 385, 13),
        Triple(2, 385, 11),
        Triple(2, 385, 7),
        Triple(2, 384, 7),
        Triple(2, 383, 7),
        Triple(2, 381, 8),
        Triple(2, 380, 8),
        Triple(2, 380, 6),
        Triple(2, 378, 13),
        Triple(2, 377, 7),
        Triple(2, 377, 6),
        Triple(2, 375, 7),
        Triple(2, 375, 15),
        //        Triple(2, 373, 7),
        Triple(2, 368, 7),
        Triple(2, 367, 7),
        Triple(2, 360, 8),
//        Triple(2, 360, 7), urlop
        Triple(2, 361, 11),
        Triple(2, 361, 7),
        Triple(2, 361, 6),
        Triple(2, 362, 10),
        Triple(2, 362, 7),
        Triple(2, 366, 7),
        Triple(2, 366, 4),
        Triple(2, 368, 8),
        Triple(2, 368, 5),
        Triple(2, 370, 7),
        Triple(2, 370, 4),
        Triple(2, 372, 7)
).apply { Collections.shuffle(this) }

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        loopForEver { startFarming() }
    }
}

fun RemoteWebDriver.startFarming() {
    val sin = loginAndGetSin()
    targets.forEach {
        try {
            attack(sin, it.first, it.second, it.third)
        } catch (e: Exception) {
            throw RuntimeException("Error while atacking ${it.first} ${it.second} ${it.third}", e)
        }
    }
}

fun RemoteWebDriver.attack(sin: String, galaxy: Int, system: Int, planet: Int) {
    val report = generateNewSpyReport(sin, galaxy, system, planet)
    if (report.resources > 2_000_000_000) {
        sendFleet(sin, galaxy, system, planet)
    }
}

