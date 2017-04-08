package io.github.ordonteam.ogame

import org.assertj.core.api.Assertions
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class BuildIntegrationTest {

    val login = "login"
    val password = "password"
    val server = "s"

    @Test
    @Ignore
    fun shouldBuild() {
        val webDriver = PhantomJSDriver()
        login(webDriver, login, password, server)
        build(webDriver, Building.DEUTERIUM)
        Assertions.assertThat(webDriver.findElementsByClassName("construction")).hasSize(2)
    }
}