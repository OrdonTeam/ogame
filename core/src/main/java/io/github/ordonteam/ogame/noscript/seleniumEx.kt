package io.github.ordonteam.ogame.noscript

fun org.openqa.selenium.WebDriver.findElementById(id: String): org.openqa.selenium.WebElement = findElement(org.openqa.selenium.By.id(id))

fun org.openqa.selenium.WebDriver.findElementsById(id: String): List<org.openqa.selenium.WebElement> = findElements(org.openqa.selenium.By.id(id))

fun org.openqa.selenium.WebDriver.findElementsByClassName(className: String): List<org.openqa.selenium.WebElement> = findElements(org.openqa.selenium.By.className(className))

fun org.openqa.selenium.WebElement.hasElementWithClassName(className: String) = findElements(org.openqa.selenium.By.className(className)).isNotEmpty()

fun org.openqa.selenium.WebDriver.hasElementWithId(id: String) = findElements(org.openqa.selenium.By.id(id)).isNotEmpty()

fun org.openqa.selenium.WebDriver.hasElementWithClassName(className: String) = findElements(org.openqa.selenium.By.className(className)).isNotEmpty()

fun org.openqa.selenium.WebDriver.waitForElementWithClassName(className: String) {
    for (i in 0..10) {
        if (hasElementWithClassName(className)) {
            return
        }
        Thread.sleep(100)
    }
}