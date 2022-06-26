package renetik.android.json

import renetik.android.core.kotlin.primitives.asDouble
import renetik.android.core.kotlin.primitives.asFloat
import renetik.android.core.kotlin.primitives.asInt
import renetik.android.core.kotlin.primitives.asLong

interface CSJsonObjectInterface {
	fun toJsonMap(): Map<String, *>

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

	fun set(key: String, string: String?)
	fun set(key: String, boolean: Boolean?) = set(key, boolean?.toString())
	fun set(key: String, int: Int?) = set(key, int?.toString())
	fun set(key: String, long: Long?) = set(key, long?.toString())
	fun set(key: String, float: Float?) = set(key, float?.toString())
	fun set(key: String, double: Double?) = set(key, double?.toString())
}