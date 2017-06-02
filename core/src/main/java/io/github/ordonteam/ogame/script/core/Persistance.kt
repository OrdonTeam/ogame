package io.github.ordonteam.ogame.script.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.github.ordonteam.ogame.script.model.SpyReport
import java.io.File

fun save(file: String, report: SpyReport) {
    File(file).writeText(GsonBuilder().setPrettyPrinting().create().toJson(report))
}

inline fun <reified T> read(fileName: String): T {
    return Gson().fromJson<T>(File(fileName).readText(), object : TypeToken<T>() {}.type)
}

inline fun <reified T> readNullable(fileName: String): T? {
    val file = File(fileName)
    if (file.exists()) {
        return read(fileName)
    } else {
        return null
    }
}