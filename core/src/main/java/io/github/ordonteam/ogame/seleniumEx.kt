package io.github.ordonteam.ogame

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

fun WebDriver.findElementById(id: String): WebElement = findElement(By.id(id))

fun WebDriver.findElementsByClassName(className: String): List<WebElement> = findElements(By.className(className))

fun WebElement.hasElementWithClassName(className: String) = findElements(By.className(className)).isNotEmpty()

fun WebDriver.hasElementWithId(id: String) = findElements(By.id(id)).isNotEmpty()
