package io.github.ordonteam.ogame.script

import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.generateNewSpyReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
    spy(sin, galaxy, system, planet)
    val report = readReport(sin, galaxy, system, planet)
    return report
}

private fun RemoteWebDriver.spy(sin: String, galaxy: Int, system: Int, planet: Int) {
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=$galaxy&system=$system&planet=$planet&planettype=1&target_mission=6")
    findElementById("210").sendKeys("1000")
    findElementByClassName("planet").click()
    findElementByXPath("//input[@type='submit']").click()
    findElementByXPath("//input[@type='submit']").click()
}

private fun RemoteWebDriver.readReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
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

private fun RemoteWebDriver.doReadReport(sin: String, galaxy: Int, system: Int, planet: Int): SpyReport {
    get("http://uni9.ogam.net.pl/index.php?page=messages&sin=$sin")
    findElementByCssSelector("body > center > center > table:nth-child(2) > tbody > tr:nth-child(4) > th:nth-child(1) > a > font").click()
    val result = findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(2)").text.replace(".", "").toLong() +
            findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(2) > td:nth-child(5)").text.replace(".", "").toLong() +
            findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody > tr:nth-child(3) > td:nth-child(2)").text.replace(".", "").toLong()
    findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > th:nth-child(2) > input[type=\"checkbox\"]").click()
    findElementByCssSelector("body > center > center > table > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1) > th > input[type=\"submit\"]").click()
    return SpyReport(result)
}

data class SpyReport(val resources: Long)
