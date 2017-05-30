package io.github.ordonteam.ogame.script.functions

import io.github.ordonteam.ogame.script.loopOnce
import org.openqa.selenium.remote.RemoteWebDriver
import javax.script.ScriptEngineManager


fun main(args: Array<String>) {
    loopOnce {
        get("file:///Users/karolkowalski/Downloads/obrona_bot2.php.html")
        recoverFromAntybot()
    }
}

private val ANTYBOT_VERSION = "body > center > form > table > tbody > tr:nth-child(1) > td"
private val ANTYBOT_QUESTION = "body > center > form > table > tbody > tr:nth-child(2) > th:nth-child(1) > input[type=\"button\"]"
private val ANTYBOT_ANSWER = "body > center > form > table > tbody > tr:nth-child(2) > th:nth-child(2) > input[type=\"text\"]"
private val ANTYBOT_CONFIRM = "body > center > form > table > tbody > tr:nth-child(2) > th:nth-child(3) > input[type=\"Submit\"]"

fun RemoteWebDriver.recoverFromAntybot() {
    try {
        val isAntybot = detectAntybot()
        System.err.println("Is Antybot = $isAntybot")
        if (isAntybot) {
            val question = antybotQuestion()
            System.err.println("Antybot question = $question")
            val answer = calculateAnswer(question)
            System.err.println("Antybot answer = $answer")
            antybotInsertAnswer(answer)
        }
    } catch (e: Exception) {
        System.err.println("Unable to recover from antybot")
        e.printStackTrace()
    }
}

private fun RemoteWebDriver.detectAntybot(): Boolean {
    val antybotVersion = findElementsByCssSelector(ANTYBOT_VERSION)
    val isAntybotVersionVisible = antybotVersion.size == 1
    if (isAntybotVersionVisible) {
        val text = antybotVersion.first().text
        return text.contains("antybot", ignoreCase = true)
    }
    return false
}

private fun RemoteWebDriver.antybotQuestion(): String {
    return findElementByCssSelector(ANTYBOT_QUESTION).getAttribute("value")
}

private fun calculateAnswer(question: String): String {
    val mgr = ScriptEngineManager()
    val engine = mgr.getEngineByName("JavaScript")
    val answer = engine.eval(question.replace("x", "*"))
    return answer.toString()
}

private fun RemoteWebDriver.antybotInsertAnswer(answer: String) {
    findElementByCssSelector(ANTYBOT_ANSWER).sendKeys(answer)
//    Runtime.getRuntime().exec("say antybot")
    Thread.sleep(4_000)
    findElementByCssSelector(ANTYBOT_CONFIRM).click()
}
