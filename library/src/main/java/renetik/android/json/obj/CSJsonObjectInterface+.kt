package renetik.android.json.obj

import renetik.android.core.kotlin.*

fun CSJsonObjectInterface.getStringList(key: String): List<String>? =
    getList(key)?.map { it.asString }

fun CSJsonObjectInterface.getIntList(key: String): List<Int?>? =
    getList(key)?.map { it?.asInt() }

fun CSJsonObjectInterface.getFloatList(key: String): List<Float?>? =
    getList(key)?.map { it?.asFloat() }

fun CSJsonObjectInterface.getDoubleList(key: String): List<Double?>? =
    getList(key)?.map { it?.asDouble() }

fun CSJsonObjectInterface.getDoubleList(key: String, default: Double)
        : List<Double>? = getList(key)?.map { it?.asDouble() ?: default }

fun CSJsonObjectInterface.getArray(key: String): Array<*>? =
    getList(key)?.toTypedArray()

fun CSJsonObjectInterface.getStringMap(key: String): Map<String, String>? =
    getMap(key)?.mapValues { it.value.asString }

fun CSJsonObjectInterface.getBooleanMap(key: String): Map<String, Boolean?>? =
    getMap(key)?.mapValues { it.value?.asBoolean() }

fun CSJsonObjectInterface.getBooleanMap(key: String, default: Boolean)
        : Map<String, Boolean>? =
    getMap(key)?.mapValues { it.value?.asBoolean() ?: default }

fun CSJsonObjectInterface.getIntMap(key: String): Map<String, Int?>? =
    getMap(key)?.mapValues { it.value?.asInt() }

fun CSJsonObjectInterface.getFloatMap(key: String): Map<String, Float?>? =
    getMap(key)?.mapValues { it.value?.asFloat() }

fun <T> CSJsonObjectInterface.getValue(key: String, values: Iterable<T>): T? {
    val savedString = get(key) ?: return null
    return values.find { it.toId() == savedString }
}