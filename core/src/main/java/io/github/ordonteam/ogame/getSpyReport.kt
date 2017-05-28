package io.github.ordonteam.ogame

import io.github.ordonteam.ogame.script.functions.generateNewSpyReport
import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.loopOnce

fun main(args: Array<String>) {
    loopOnce {
        val sin = loginAndGetSin()
        val report = generateNewSpyReport(sin, 2, 0, 0)
        System.err.println()
        System.err.println()
        System.err.println()
        System.err.println(report.fleet)
        System.err.println(report.defence)
        System.err.println(report.isDefenceless)
        System.err.println()
        System.err.println()
        System.err.println()
    }
}