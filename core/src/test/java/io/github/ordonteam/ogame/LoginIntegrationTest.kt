package io.github.ordonteam.ogame

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class LoginIntegrationTest {

    val login = "login"
    val password = "password"
    val server = "s"

    @Test
    @Ignore
    fun shouldLogin() {
        val webDriver = PhantomJSDriver()
        login(webDriver, login, password, server)
        assertThat(webDriver.findElementById("playerName").text).contains(login)
    }
}