package io.github.ordonteam.ogame

import io.github.ordonteam.ogame.script.functions.generateNewSpyReport
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.loopOnce

fun main(args: Array<String>) {
    loopOnce {
        val sin = loginAndGetSin()
        val report = generateNewSpyReport(sin, 2, 0, 0)
        println()
        println()
        println()
        println(report.fleet)
        println(report.defence)
        println(report.isDefenceless)
        println()
        println()
        println()
    }
}