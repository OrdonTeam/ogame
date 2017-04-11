package io.github.ordonteam.ogame

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class LoginIntegrationTest {

    @Test
    @Ignore
    fun shouldLogin() {
        val webDriver = PhantomJSDriver()
        loginHelper(webDriver)
        assertThat(webDriver.findElementById("playerName").text).contains(login)
    }
}
