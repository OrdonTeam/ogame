package io.github.ordonteam.ogame

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun WebDriver.findElementById(id: String) = findElement(By.id(id))!!