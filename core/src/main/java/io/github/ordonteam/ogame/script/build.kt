package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.core.getTradeRequests
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.Select

object Build {
    @JvmStatic
    fun main(args: Array<String>) {
        loopOnce { build() }
    }
}

private fun RemoteWebDriver.build() {
    val sin = loginAndGetSin()
    buildOn(sin, "151623")
    buildOn(sin, "151813")
    buildOn(sin, "151627")
    buildOn(sin, "151785")
    buildOn(sin, "151790")
    buildOn(sin, "151804")
    buildOn(sin, "151631")
    buildOn(sin, "151517")
}

private fun RemoteWebDriver.buildOn(sin: String, cp: String) {
    get("http://uni9.ogam.net.pl/index.php?page=marchand&sin=$sin&cp=$cp&mode=&re=0")
    get("http://uni9.ogam.net.pl/index.php?page=marchand&sin=$sin&cp=$cp&mode=&re=0")
    val metal = findElementByCssSelector("#resources > tbody > tr:nth-child(3) > td:nth-child(1)").text.split("\n")[0].replace(".", "").trim().toLong()
    val crystal = findElementByCssSelector("#resources > tbody > tr:nth-child(3) > td:nth-child(2)").text.split("\n")[0].replace(".", "").trim().toLong()
    val deuterium = findElementByCssSelector("#resources > tbody > tr:nth-child(3) > td:nth-child(3)").text.split("\n")[0].replace(".", "").trim().toLong()
    val (metalToTrade, crystalToTrade, deuteriumToTrade) = getTradeRequests(metal, crystal, deuterium)
    tradeMetal(metalToTrade)
    tradeCrystal(sin, cp, crystalToTrade)
    tradeDeuterium(sin, cp, deuteriumToTrade)
    doBuild(sin)
    Thread.sleep(1000)
}

private fun RemoteWebDriver.doBuild(sin: String) {
    Thread.sleep(1000)
    get("http://uni9.ogam.net.pl/index.php?page=buildings&mode=fleet&sin=$sin")
    Thread.sleep(1000)
    findElementByCssSelector("body > center > form > table:nth-child(6) > tbody > tr:nth-child(13) > th:nth-child(3) > span > a").click()
    Thread.sleep(1000)
    findElementByCssSelector("body > center > form > table:nth-child(6) > tbody > tr:nth-child(17) > th > input[type=\"Submit\"]").click()
}

private fun RemoteWebDriver.tradeMetal(metalToTrade: Pair<Long, Long>) {
    if (metalToTrade.first + metalToTrade.second > 0L) {
        Thread.sleep(1000)
        findElementByCssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(2) > th:nth-child(1) > input[type=\"submit\"]").click()
        findElementById("1").sendKeys(metalToTrade.first.toString())
        findElementById("2").sendKeys(metalToTrade.second.toString())
        findElementByCssSelector("#marchand > table > tbody > tr:nth-child(5) > th > input[type=\"submit\"]").click()
        Thread.sleep(1000)
    }
}

private fun RemoteWebDriver.tradeCrystal(sin: String, cp: String, crystalToTrade: Pair<Long, Long>) {
    if (crystalToTrade.first + crystalToTrade.second > 0L) {
        get("http://uni9.ogam.net.pl/index.php?page=marchand&sin=$sin&cp=$cp&mode=&re=0")
        Thread.sleep(1000)
        Select(findElementByCssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(2) > th:nth-child(1) > select")).selectByValue("cristal")
        findElementByCssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(2) > th:nth-child(1) > input[type=\"submit\"]").click()
        findElementById("1").sendKeys(crystalToTrade.first.toString())
        findElementById("2").sendKeys(crystalToTrade.second.toString())
        findElementByCssSelector("#marchand > table > tbody > tr:nth-child(6) > th > input[type=\"submit\"]").click()
        Thread.sleep(1000)
    }
}


private fun RemoteWebDriver.tradeDeuterium(sin: String, cp: String, deuteriumToTrade: Pair<Long, Long>) {
    if (deuteriumToTrade.first + deuteriumToTrade.second > 0L) {
        get("http://uni9.ogam.net.pl/index.php?page=marchand&sin=$sin&cp=$cp&mode=&re=0")
        Thread.sleep(1000)
        Select(findElementByCssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(2) > th:nth-child(1) > select")).selectByValue("deut")
        findElementByCssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(2) > th:nth-child(1) > input[type=\"submit\"]").click()
        findElementById("1").sendKeys(deuteriumToTrade.first.toString())
        findElementById("2").sendKeys(deuteriumToTrade.second.toString())
        findElementByCssSelector("#marchand > table > tbody > tr:nth-child(5) > th > input[type=\"submit\"]").click()
        Thread.sleep(1000)
    }
}
