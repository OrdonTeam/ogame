package io.github.ordonteam.ogame.script.core

fun getTradeRequests(metal: Long, crystal: Long, deuterium: Long): Triple<Pair<Long, Long>, Pair<Long, Long>, Pair<Long, Long>> {
    val totalInMetal = metal + 2 * crystal + 4 * deuterium
    val deathStarCostInMetal = 5_000_000 + 4_000_000 * 2 + 1_000_000 * 4
    val deathStars = totalInMetal / deathStarCostInMetal
    val metalUsage = deathStars * 5_000_000
    val crystalUsage = deathStars * 4_000_000
    val deuteriumUsage = deathStars * 1_000_000
    val metalToTrade = metal - metalUsage
    val crystalToTrade = crystal - crystalUsage
    val deuteriumToTrade = deuterium - deuteriumUsage
    return Triple(
            tradeMetal(metalToTrade, crystalToTrade, deuteriumToTrade),
            tradeCrystal(metalToTrade, crystalToTrade, deuteriumToTrade),
            tradeDeuterium(metalToTrade, crystalToTrade, deuteriumToTrade))
}

fun tradeMetal(metalToTrade: Long, crystalToTrade: Long, deuteriumToTrade: Long): Pair<Long, Long> {
    return if (metalToTrade < 0) {
        0L to 0L
    } else {
        val crystal = if (crystalToTrade < 0) {
            Math.min(-crystalToTrade, metalToTrade / 2)
        } else {
            0
        }
        val deuterium = if (deuteriumToTrade < 0) {
            metalToTrade / 4
        } else {
            0
        }
        crystal to deuterium
    }
}

fun tradeCrystal(metalToTrade: Long, crystalToTrade: Long, deuteriumToTrade: Long): Pair<Long, Long> {
    return if (crystalToTrade < 0) {
        0L to 0L
    } else {
        val metal = if (metalToTrade < 0) {
            Math.min(-metalToTrade, crystalToTrade * 2)
        } else {
            0
        }
        val deuterium = if (deuteriumToTrade < 0) {
            crystalToTrade / 2
        } else {
            0
        }
        metal to deuterium
    }
}

fun tradeDeuterium(metalToTrade: Long, crystalToTrade: Long, deuteriumToTrade: Long): Pair<Long, Long> {
    return if (deuteriumToTrade < 0) {
        0L to 0L
    } else {
        val metal = if (metalToTrade < 0) {
            Math.min(-metalToTrade, deuteriumToTrade * 4)
        } else {
            0
        }
        val crystal = if (crystalToTrade < 0) {
            Math.min(-crystalToTrade, deuteriumToTrade * 2)
        } else {
            0
        }
        metal to crystal
    }
}
