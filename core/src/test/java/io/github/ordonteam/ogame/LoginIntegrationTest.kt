package io.github.ordonteam.ogame

import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class LoginIntegrationTest {

    @Test
    @Ignore
    fun shouldLogin() {
        val webDriver = PhantomJSDriver()
        loginHelper(webDriver)
        assertTrue(isLoggedIn(webDriver))
    }
}
