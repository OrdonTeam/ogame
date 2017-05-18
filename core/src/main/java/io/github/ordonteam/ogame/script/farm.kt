package io.github.ordonteam.ogame.script

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
    spy(sin, galaxy, system, planet)
    if (readReport(sin, galaxy, system, planet) > 2_000_000_000) {
        sendFleet(sin, galaxy, system, planet)
    }
}

private fun RemoteWebDriver.spy(sin: String, galaxy: Int, system: Int, planet: Int) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=$galaxy&system=$system&planet=$planet&planettype=1&target_mission=6")
    findElementById("210").sendKeys("1000")
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}

private fun RemoteWebDriver.readReport(sin: String, galaxy: Int, system: Int, planet: Int): Long {
    try {
        Thread.sleep(3000)
        return doReadReport(sin, galaxy, system, planet)
    } catch (e: Exception) {
        try {
            Thread.sleep(3000)
            return doReadReport(sin, galaxy, system, planet)
        } catch (e: Exception) {
            Thread.sleep(3000)
            return doReadReport(sin, galaxy, system, planet)
        }
    }
}

private fun RemoteWebDriver.doReadReport(sin: String, galaxy: Int, system: Int, planet: Int): Long {
    get("http://uni9.ogam.net.pl/index.php?page=messages&sin=$sin")
    findElementByCssSelector("body > center > center > table:nth-child(2) > tbody > tr:nth-child(4) > th:nth-child(1) > a > font").click()
    val result = findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(2)").text.replace(".", "").toLong() +
            findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(5)").text.replace(".", "").toLong() +
            findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(3) > td:nth-child(2)").text.replace(".", "").toLong()
    findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > th:nth-child(2) > input[type=\"checkbox\"]").click()
    findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1) > th > input[type=\"submit\"]").click()
    return result
}

private fun RemoteWebDriver.sendFleet(sin: String, galaxy: Int, system: Int, planet: Int) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=$galaxy&system=$system&planet=$planet&planettype=1&target_mission=1")
    findElementById("217").sendKeys("500000")
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}

private fun RemoteWebDriver.debug() {
    while (true) {
        try {
            findElementsByCssSelector(Scanner(System.`in`).nextLine()).forEachIndexed { i, it -> println(i.toString() + " " + it.text) }
        } catch (e: Exception) {

        }
    }
}
