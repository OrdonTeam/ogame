package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.functions.generateNewSpyReport
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.functions.sendFleet
import io.github.ordonteam.ogame.script.model.Fleet
import io.github.ordonteam.ogame.script.model.Position
import io.github.ordonteam.ogame.script.model.Ship
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.*

val targets = mutableListOf(
        Position(2, 351, 7),
        Position(2, 351, 6),
        Position(2, 352, 7),
        Position(2, 352, 1),
        Position(2, 353, 10),
        Position(2, 353, 9),
        Position(2, 353, 7),
        Position(2, 357, 6),
        Position(2, 358, 6),
        Position(2, 359, 7),
        Position(2, 400, 8),
        Position(2, 400, 7),
        Position(2, 399, 7),
        Position(2, 399, 8),
        Position(2, 396, 7),
        Position(2, 393, 9),
        Position(2, 392, 7),
        Position(2, 391, 9),
        Position(2, 391, 8),
        Position(2, 390, 7),
        Position(2, 389, 7),
        Position(2, 387, 4),
        Position(2, 386, 12),
        Position(2, 385, 13),
        Position(2, 385, 11),
        Position(2, 385, 7),
        Position(2, 384, 7),
        Position(2, 383, 7),
        Position(2, 381, 8),
        Position(2, 380, 8),
        Position(2, 380, 6),
        Position(2, 378, 13),
        Position(2, 377, 7),
        Position(2, 377, 6),
        Position(2, 375, 7),
        Position(2, 375, 15),
        Position(2, 368, 7),
        Position(2, 367, 7),
        Position(2, 360, 8),
        Position(2, 361, 11),
        Position(2, 361, 7),
        Position(2, 361, 6),
        Position(2, 362, 10),
        Position(2, 362, 7),
        Position(2, 366, 7),
        Position(2, 366, 4),
        Position(2, 368, 8),
        Position(2, 368, 5),
        Position(2, 370, 7),
        Position(2, 370, 4),
        Position(2, 372, 7)
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
            attack(sin, it)
        } catch (e: Exception) {
            throw RuntimeException("Error while attacking ${it.galaxy} ${it.system} ${it.planet}", e)
        }
    }
}

fun RemoteWebDriver.attack(sin: String, position: Position) {
    val report = generateNewSpyReport(sin, position.galaxy, position.system, position.planet)
    if (report.resources > 2_000_000_000) {
        sendFleet(sin, position, Fleet(mapOf(Ship.ULTRA_TRANSPORTER to 500_000L)))
    }
}

