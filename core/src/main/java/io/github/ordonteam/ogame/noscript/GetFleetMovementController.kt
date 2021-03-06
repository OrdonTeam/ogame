package io.github.ordonteam.ogame.noscript

import org.springframework.web.bind.annotation.RestController

@RestController
class GetFleetMovementController {

    private val login = System.getenv("OGAME_LOGIN")
    private val password = System.getenv("OGAME_PASSWORD")
    private val server = System.getenv("OGAME_SERVER")
    private val webDriver = org.openqa.selenium.phantomjs.PhantomJSDriver()

    @org.springframework.web.bind.annotation.GetMapping("/fleet_movement")
    fun getFleetMovement(): FleetMoments {
        if (!isLoggedIn(webDriver)) {
            login(webDriver, login, password, server)
        }
        return FleetMoments(getFleetMovement(webDriver), System.currentTimeMillis())
    }

    data class FleetMoments(val fleet_movement: List<FleetMovement>, val timestamp: Long)
}