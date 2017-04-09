package io.github.ordonteam.ogame

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class GetFleetMovementIntegrationTest {

    val login = "login"
    val password = "password"
    val server = "s"

    @Test
    @Ignore
    fun shouldGetFleetMovement() {
        val webDriver = PhantomJSDriver()
        login(webDriver, login, password, server)
        assertThat(getFleetMovement(webDriver)).hasSize(2)
    }
}