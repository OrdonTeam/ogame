package io.github.ordonteam.ogame.script.model

data class Statistics(
        val players: Map<PlayerId, Player>) {
    fun updatePlayer(playerId: String, updatePlayer: Player.() -> Player): Statistics {
        return copy(players = players.computeWithDefault(playerId, Player(playerId), updatePlayer))
    }
}

typealias PlayerId = String

data class Player(
        val id: String,
        val name: Value<String>?,
        val points: Value<Long>?,
        val fleet: Value<Long>?,
        val status: Value<Status>?) {
    constructor(id: String) : this(id, null, null, null, null)
}

enum class Status {
    ACTIVE,
    INACTIVE,
    BANNED
}

data class Value<out T>(
        val value: T,
        val updatedAt: Long = java.lang.System.currentTimeMillis())

fun <Key, Value> Map<Key, Value>.computeWithDefault(key: Key, default: Value, update: Value.() -> Value): Map<Key, Value> {
    return toMutableMap().apply { put(key, (this[key] ?: default).update()) }
}