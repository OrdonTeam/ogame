package io.github.ordonteam.ogame

import io.github.ordonteam.ogame.script.functions.loginAndGetSin
import io.github.ordonteam.ogame.script.functions.readAllReports
import io.github.ordonteam.ogame.script.loopOnce

fun main(args: Array<String>) {
    loopOnce {
        val sin = loginAndGetSin()
        val report = readAllReports(sin)
        System.err.println()
        System.err.println()
        System.err.println()
        System.err.println(report)
        System.err.println()
        System.err.println()
        System.err.println()
    }
}