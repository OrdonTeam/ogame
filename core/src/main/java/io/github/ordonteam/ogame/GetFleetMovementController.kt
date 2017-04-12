//package io.github.ordonteam.ogame
//
//import org.openqa.selenium.WebDriver
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.GetMapping
//
//@Controller
//class GetFleetMovementController(private val webDriver: WebDriver) {
//
//    private val login = System.getenv("OGAME_LOGIN")
//    private val password = System.getenv("OGAME_PASSWORD")
//    private val server = System.getenv("OGAME_SERVER")
//
//    @GetMapping("/fleet_movement")
//    fun getFleetMovement(): List<FleetMovement> {
//        if (!isLoggedIn(webDriver, login)) {
//            login(webDriver, login, password, server)
//        }
//        return getFleetMovement(webDriver)
//    }
//}