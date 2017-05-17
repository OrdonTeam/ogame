package io.github.ordonteam.ogame.script

import java.util.*

object Trade {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        val metal = scanner.next().replace(".", "").trim().toLong()
        scanner.next().replace(".", "").trim().toLong()
        val crystal = scanner.next().replace(".", "").trim().toLong()
        scanner.next().replace(".", "").trim().toLong()
        val deuterium = scanner.next().replace(".", "").trim().toLong()
        printResult(metal, crystal, deuterium)
    }

    private fun printResult(metal: Long, crystal: Long, deuterium: Long) {
        val totalInMetal = metal + 2 * crystal + 4 * deuterium
        val deathStarCostInMetal = 5_000_000 + 4_000_000 * 2 + 1_000_000 * 4
        val deathStars = totalInMetal / deathStarCostInMetal
        val metalUsage = deathStars * 5_000_000
        val crystalUsage = deathStars * 4_000_000
        val deuteriumUsage = deathStars * 1_000_000
        val rest = totalInMetal - deathStarCostInMetal * deathStars
        val restInDeuterium = rest / 4
        println()
        println("${metal - metalUsage} ${(metal - metalUsage)/2}")
        println(crystal - crystalUsage)
        println(deuterium - deuteriumUsage - restInDeuterium)
    }
}
//1.092.143.881
//22.730.706
//1.811.148