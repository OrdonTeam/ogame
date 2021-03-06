package io.github.ordonteam.ogame

import io.github.ordonteam.ogame.noscript.Building
import io.github.ordonteam.ogame.noscript.build
import org.assertj.core.api.Assertions
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class BuildIntegrationTest {

    @Test
    @Ignore
    fun shouldBuild() {
        val webDriver = PhantomJSDriver()
        loginHelper(webDriver)
        build(webDriver, Building.DEUTERIUM)
        Assertions.assertThat(webDriver.findElementsByClassName("construction")).hasSize(2)
    }
}