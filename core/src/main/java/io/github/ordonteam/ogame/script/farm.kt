package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.core.readNullable
import io.github.ordonteam.ogame.script.core.save
import io.github.ordonteam.ogame.script.core.simulate
import io.github.ordonteam.ogame.script.functions.*
import io.github.ordonteam.ogame.script.grep.readPlanetsFromPage
import io.github.ordonteam.ogame.script.model.*
import org.openqa.selenium.remote.RemoteWebDriver
import java.io.OutputStream
import java.io.PrintStream
import java.lang.System
import java.util.*

val target1 = Triple("155506", 1, 1..250)
val target2 = Triple("157445", 2, 1..250)
val target3 = Triple("155495", 3, 1..250)
val target4 = Triple("155498", 3, 250..499)
val target5 = Triple("157696", 4, 1..250)
val target6 = Triple("157698", 4, 250..499)
val targets = mutableListOf(target1, target2, target3, target4, target5, target6)

object Main {

    init {
        java.lang.System.setOut(PrintStream(object : OutputStream() {
            override fun write(b: Int) {

            }
        }))
    }

    @JvmStatic
    fun main(args: Array<String>) {
        loopForEver {
            val sin = loginAndGetSin()
            targets.apply { Collections.shuffle(this) }
                    .forEach { (cp, galaxy, systems) ->
                        doDeposits(sin)
                        startFarming(sin, cp, galaxy, systems)
                    }
        }
    }

    private fun RemoteWebDriver.doDeposits(sin: String) {
        targets.forEach { (first) ->
            get("http://uni9.ogam.net.pl/index.php?page=marchand&sin=$sin&cp=$first&mode=&re=0")
            deposit(sin)
        }
    }
}

fun RemoteWebDriver.startFarming(sin: String, cp: String, galaxy: Int, systems: IntRange) {
    get("http://uni9.ogam.net.pl/index.php?page=marchand&sin=$sin&cp=$cp&mode=&re=0")
    systems.toMutableList().apply { Collections.shuffle(this) }.forEach { system ->
        readPlanetsFromPage(sin, galaxy, system)
                .filter { it.isIdle.value == Status.INACTIVE }
                .forEach {
                    try {
                        attack(sin, it.position)
                    } catch (e: Exception) {
                        throw RuntimeException("Error while attacking ${it.position.galaxy} ${it.position.system} ${it.position.planet}", e)
                    }
                }
    }
}

fun RemoteWebDriver.attack(sin: String, position: Position) {
    val previousReport = readPreviousReport(position)
    if (previousReport == null) {
        spyAndAttack(sin, position)
    } else {
        if (previousReport.resources > 24_000_000) {
            spyAndAttack(sin, position)
        } else {
            System.err.println("SKIPPING SPYING PREVIOUS REPORT BELOW")
            printReport(position, previousReport)
        }
    }
}

private fun RemoteWebDriver.spyAndAttack(sin: String, position: Position) {
    val report = generateNewSpyReport(sin, position.galaxy, position.system, position.planet)
    saveReport(report, position)
    printReport(position, report)
    attackIfValuable(report, sin, position)
}

private fun printReport(position: Position, report: SpyReport) {
    System.err.println("-----------------------------")
    System.err.println("${position.galaxy} ${position.system} ${position.planet}")
    System.err.println("${report.resources}")
    System.err.println("${report.resourcesValue}")
    System.err.println("${report.fleet}")
    System.err.println("${report.defence}")
    System.err.println("${report.isEasilyBeatable}")
    System.err.println("-----------------------------")
}

fun readPreviousReport(position: Position): SpyReport? {
    return readNullable("ogam/reports/${position.galaxy}-${position.system}-${position.planet}.json")
}

fun saveReport(report: SpyReport, position: Position) {
    save("ogam/reports/${position.galaxy}-${position.system}-${position.planet}.json", report)
}

private fun RemoteWebDriver.attackIfValuable(report: SpyReport, sin: String, position: Position) {
    val fleet = Fleet(mapOf(Ship.ULTRA_TRANSPORTER to report.resources / 300_000))
    if (report.resourcesValue > 10_000_000_000 && simulate(fleet, report.fleet, report.defence)) {
        System.err.println("ATTACKING")
        sendFleet(sin, position, fleet, Mission.ATTACK)
        attackIfValuable(report.afterAttack(), sin, position)
    }
}

