package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.core.read
import io.github.ordonteam.ogame.script.model.Position
import io.github.ordonteam.ogame.script.model.SpyReport
import java.io.File

fun main(args: Array<String>) {
    readReports()
}

fun readReports() {
    File("ogam/reports").listFiles().map {
        it.name.toPosition() to read<SpyReport>(it)
    }.filter {
        it.second.defence.utilities.map { it.value }.sum() > 0
    }.sortedBy {
        it.second.resourcesValue / it.second.defence.utilities.map { it.value }.sum()
    }.forEach {
        println(it)
    }
}

private fun String.toPosition(): Position {
    val position = take(indexOf(".")).split("-").map { it.toInt() }
    return Position(position[0], position[1], position[2])
}
