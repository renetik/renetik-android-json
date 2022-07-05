package renetik.android.json.obj

import renetik.android.core.kotlin.primitives.asDouble
import renetik.android.core.kotlin.primitives.asFloat
import renetik.android.core.kotlin.primitives.asInt
import renetik.android.core.kotlin.primitives.asLong
import kotlin.reflect.KClass

interface CSJsonObjectInterface : Iterable<Map.Entry<String, Any?>> {
    val jsonMap: Map<String, *>
    override fun iterator(): Iterator<Map.Entry<String, Any?>> = jsonMap.iterator()

    fun has(key: String): Boolean = jsonMap.containsKey(key)
    fun get(key: String): String? = jsonMap[key]?.toString()

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

    fun getList(key: String) = jsonMap[key] as? List<Any?>

    @Suppress("UNCHECKED_CAST")
    fun getMap(key: String) = jsonMap[key] as? Map<String, Any?>

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