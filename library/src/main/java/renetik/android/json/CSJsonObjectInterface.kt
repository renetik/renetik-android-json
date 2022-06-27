package renetik.android.json

import renetik.android.core.kotlin.*
import renetik.android.core.kotlin.primitives.asDouble
import renetik.android.core.kotlin.primitives.asFloat
import renetik.android.core.kotlin.primitives.asInt
import renetik.android.core.kotlin.primitives.asLong
import kotlin.reflect.KClass

interface CSJsonObjectInterface {
	fun toJsonMap(): Map<String, *>

	fun has(key: String): Boolean = toJsonMap().containsKey(key)
	fun get(key: String): String? = toJsonMap()[key]?.toString()

	fun getString(key: String, default: String): String = get(key) ?: default
	fun getString(key: String): String? = get(key)

	fun getBoolean(key: String, default: Boolean): Boolean = get(key)?.toBoolean() ?: default
	fun getBoolean(key: String, default: Boolean? = null): Boolean? =
		get(key)?.toBoolean() ?: default

	fun getInt(key: String, default: Int): Int = get(key)?.asInt() ?: default
	fun getInt(key: String, default: Int? = null): Int? = get(key)?.asInt() ?: default

	fun getLong(key: String, default: Long): Long = get(key)?.asLong() ?: default
	fun getLong(key: String, default: Long? = null): Long? = get(key)?.asLong() ?: default

	fun getFloat(key: String, default: Float): Float = get(key)?.asFloat() ?: default
	fun getFloat(key: String, default: Float? = null): Float? = get(key)?.asFloat() ?: default

	fun getDouble(key: String, default: Double): Double = get(key)?.asDouble() ?: default
	fun getDouble(key: String, default: Double? = null): Double? = get(key)?.asDouble() ?: default

	fun getList(key: String) = toJsonMap()[key] as? List<Any?>

	@Suppress("UNCHECKED_CAST")
	fun getMap(key: String) = toJsonMap()[key] as? Map<String, Any?>

	fun <T : CSJsonObject> getJsonObject(key: String, type: KClass<T>): T?
	fun <T : CSJsonObject> getJsonObjectList(key: String, type: KClass<T>): List<T>?

	fun set(key: String, string: String?)
	fun set(key: String, boolean: Boolean?) = set(key, boolean?.toString())
	fun set(key: String, int: Int?) = set(key, int?.toString())
	fun set(key: String, long: Long?) = set(key, long?.toString())
	fun set(key: String, float: Float?) = set(key, float?.toString())
	fun set(key: String, double: Double?) = set(key, double?.toString())
	fun set(key: String, value: Array<*>?)
	fun set(key: String, value: List<*>?)
	fun set(key: String, value: Map<String, *>?)
	fun <T : CSJsonObject> set(key: String, value: T?)
}

fun CSJsonObjectInterface.getStringList(key: String): List<String?>? =
	getList(key)?.map { it?.asString }

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