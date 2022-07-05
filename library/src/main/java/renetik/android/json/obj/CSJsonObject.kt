package renetik.android.json.obj

import renetik.android.core.kotlin.collections.at
import renetik.android.core.kotlin.run
import renetik.android.json.*
import kotlin.reflect.KClass

@Suppress("unchecked_cast")
open class CSJsonObject : CSJsonObjectInterface {

    open val data: MutableMap<String, Any?> = mutableMapOf()
    open fun load(data: Map<String, Any?>) {
        this.data.putAll(data)
        onLoaded()
    }

    open fun onLoaded() = Unit
    open fun onChange() = Unit
    open fun clear() = data.clear()
    open fun clear(key: String) = data.run { it.remove(key) }

    override fun <T : CSJsonObject> getJsonObject(key: String, type: KClass<T>): T? =
        data[key] as? T ?: (data[key] as? MutableMap<String, Any?>)
            ?.let { map -> type.createJsonObject(map).also { data[key] = it } }

    override fun <T : CSJsonObject> getJsonObjectList(key: String, type: KClass<T>): List<T>? {
        val isFirstItemJsonObject = ((data[key] as? List<*>)?.at(0) as? T) != null
        return if (isFirstItemJsonObject) data[key] as List<T> else
            (data[key] as? List<MutableMap<String, Any?>>)?.let { list ->
                type.createJsonObjectList(list).also { data[key] = it }
            }
    }

    fun <T : CSJsonObject> getJsonObjectMap(
        key: String, type: KClass<T>): Map<String, T>? {
        val isFirstItemJsonObject = ((data[key] as? Map<String, *>)
            ?.values?.firstOrNull() as? T) != null
        return if (isFirstItemJsonObject) data[key] as Map<String, T> else
            (data[key] as? Map<String, MutableMap<String, Any?>>)?.let { map ->
                type.createJsonObjectMap(map).also { data[key] = it }
            }
    }

    private fun setValue(key: String, value: Any?) {
        val jsonValue = value.toJsonType()
        if (value != null && data[key] == jsonValue) return
        data[key] = jsonValue
        onChange()
    }

    override fun set(key: String, string: String?) = setValue(key, string)
    override fun set(key: String, boolean: Boolean?) = setValue(key, boolean)
    override fun set(key: String, int: Int?) = setValue(key, int)
    override fun set(key: String, long: Long?) = setValue(key, long)
    override fun set(key: String, float: Float?) = setValue(key, float)
    override fun set(key: String, double: Double?) = setValue(key, double)

    override fun set(key: String, value: Array<*>?) {
        if (value != null && data[key] == value) return
        data[key] = value?.toJSONArray()
        onChange()
    }

    override fun set(key: String, value: List<*>?) {
        if (value != null && data[key] == value) return
        data[key] = value?.toJSONArray()
        onChange()
    }

    override fun set(key: String, value: Map<String, *>?) {
        if (value != null && data[key] == value) return
        data[key] = value?.toJSONObject()
        onChange()
    }

    override fun <T : CSJsonObject> set(key: String, value: T?) {
        if (value != null && data[key] == value) return
        data[key] = value
        onChange()
    }

    override val jsonMap: Map<String, *> by lazy { data }
    override fun toString() = super.toString() + toJson()
    override fun equals(other: Any?) =
        (other as? CSJsonObject)?.let { it.data == data } ?: super.equals(other)

    override fun hashCode() = data.hashCode()
}