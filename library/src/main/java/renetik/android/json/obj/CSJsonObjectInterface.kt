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
    fun clear(key: String)
    fun clear()
    fun get(key: String): String? = jsonMap[key]?.toString()

    fun getString(key: String, default: String): String = get(key) ?: default
    fun getString(key: String): String? = get(key)
    fun set(key: String, string: String?)

    fun getBoolean(key: String, default: Boolean): Boolean = get(key)?.toBoolean() ?: default
    fun getBoolean(key: String, default: Boolean? = null): Boolean? =
        get(key)?.toBoolean() ?: default

    fun set(key: String, boolean: Boolean?) = set(key, boolean?.toString())

    fun getInt(key: String, default: Int): Int = get(key)?.asInt() ?: default
    fun getInt(key: String, default: Int? = null): Int? = get(key)?.asInt() ?: default
    fun set(key: String, int: Int?) = set(key, int?.toString())

    fun getLong(key: String, default: Long): Long = get(key)?.asLong() ?: default
    fun getLong(key: String, default: Long? = null): Long? = get(key)?.asLong() ?: default
    fun set(key: String, long: Long?) = set(key, long?.toString())

    fun getFloat(key: String, default: Float): Float = get(key)?.asFloat() ?: default
    fun getFloat(key: String, default: Float? = null): Float? = get(key)?.asFloat() ?: default
    fun set(key: String, float: Float?) = set(key, float?.toString())

    fun getDouble(key: String, default: Double): Double = get(key)?.asDouble() ?: default
    fun getDouble(key: String, default: Double? = null): Double? = get(key)?.asDouble() ?: default
    fun set(key: String, double: Double?) = set(key, double?.toString())

    fun getList(key: String) = jsonMap[key] as? List<Any?>
    fun set(key: String, value: List<*>?)

    fun set(key: String, value: Array<*>?)

    @Suppress("UNCHECKED_CAST")
    fun getMap(key: String) = jsonMap[key] as? Map<String, Any?>
    fun set(key: String, value: Map<String, *>?)

//    fun <T : CSJsonObject> getJsonObjectMap(key: String, type: KClass<T>): Map<String, T>?
//    fun <T : CSJsonObject> setJsonObjectMap(key: String, map: Map<String, T>?)

    fun <T : CSJsonObject> getJsonObject(key: String, type: KClass<T>): T?
    fun <T : CSJsonObject> set(key: String, value: T?)

    fun <T : CSJsonObject> getJsonObjectList(key: String, type: KClass<T>): List<T>?
    fun <T : CSJsonObject> setJsonObjectList(key: String, list: List<T>?)
}