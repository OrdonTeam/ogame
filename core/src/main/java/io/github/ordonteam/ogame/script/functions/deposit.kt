package io.github.ordonteam.ogame.script.functions

import org.openqa.selenium.remote.RemoteWebDriver

val DEUTERIUM = "#resources > tbody > tr:nth-child(3) > td:nth-child(3)"
val DEUTERIUM_INPUT = "body > center > form > table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(2) > input[type=\"text\"]"
val DEPOSIT = "body > center > form > table > tbody:nth-child(1) > tr:nth-child(5) > th > input[type=\"Submit\"]"
val WAIT = "body > center > table > tbody > tr:nth-child(2) > th > table > tbody > tr > th > font > blink"

fun RemoteWebDriver.deposit(sin: String) {
    get("http://uni9.ogam.net.pl/index.php?page=bankacc&mode=wplata_w&sin=$sin")
    val deuterium = findElementByCssSelector(DEUTERIUM).text.split("\n")[0].replace(".", "").trim().toLong()
    if (deuterium > 2_000_000_000L) {
        findElementByCssSelector(DEUTERIUM_INPUT).clear()
        findElementByCssSelector(DEUTERIUM_INPUT).sendKeys((deuterium - 1_000_000_000L).toString())
        findElementByCssSelector(DEPOSIT).click()
        (1..10).forEach {
            if (findElementsByCssSelector(WAIT).isEmpty()) {
                return
            } else {
                Thread.sleep(1000L)
            }
        }
    }
}