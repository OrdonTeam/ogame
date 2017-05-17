package io.github.ordonteam.ogame.script

import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.loginAndGetSin(): String {
    get("http://ogam.net.pl")
    findElementByCssSelector("#log_box > div:nth-child(3) > input[type=\"text\"]").sendKeys(System.getenv("OGAME_LOGIN"))
    findElementByCssSelector("#log_box > div:nth-child(4) > input[type=\"password\"]:nth-child(4)").sendKeys(System.getenv("OGAME_PASSWORD"))
    findElementById("zaloguj").click()
    get("http://uni9.ogam.net.pl/index.php?page=fleet&from=rs&mode=raport&galaxy=2&system=373&planet=5&planettype=1&target_mission=6")
    return findElementByCssSelector("#resources > tbody > tr:nth-child(3) > td:nth-child(6) > a").getAttribute("href").drop("http://uni9.ogam.net.pl/index.php?page=shop&sin=".length)
}