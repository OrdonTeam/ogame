package io.github.ordonteam.ogame

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class GetFleetMovementIntegrationTest {

    @Test
    @Ignore
    fun shouldGetFleetMovement() {
        val webDriver = PhantomJSDriver()
        loginHelper(webDriver)
        assertThat(getFleetMovement(webDriver)).hasSize(2)
    }
}