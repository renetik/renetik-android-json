package renetik.android.json.obj

import renetik.android.json.createJsonObject
import renetik.android.json.createJsonObjectList
import renetik.android.json.createJsonObjectMap
import renetik.android.json.toJsonType
import kotlin.reflect.KClass

@Suppress("unchecked_cast")
open class CSJsonObject : CSJsonObjectInterface {

    open val data: MutableMap<String, Any?> = mutableMapOf()
    open fun load(data: Map<String, Any?>) {
        this.data.putAll(data)
        onLoaded()
        onChange()
    }

    open fun onLoaded() = Unit
    open fun onChange() = Unit
    override fun clear() {
        if (data.isEmpty()) return
        data.clear()
        onLoaded()
        onChange()
    }

    override fun clear(key: String) {
        if (data.remove(key) == null) return
        onChange()
    }

    override fun <T : CSJsonObject> getJsonObject(key: String, type: KClass<T>): T? =
        (data[key] as? MutableMap<String, Any?>)?.let(type::createJsonObject)

    override fun <T : CSJsonObject> setJsonObject(key: String, value: T?) {
        val map: Map<String, *>? = value?.jsonMap
        if (data[key] == map) return
        data[key] = map?.toMutableMap()
        onChange()
    }

    override fun <T : CSJsonObject> getJsonObjectList(key: String, type: KClass<T>): List<T>? =
        (data[key] as? List<MutableMap<String, Any?>>)?.let { type.createJsonObjectList(it) }

    override fun <T : CSJsonObject> setJsonObjectList(key: String, list: List<T>?) {
        val value: List<MutableMap<String, Any?>>? =
            list?.let { List(list.size) { list[it].data } }
        if (data[key] == value) return
        data[key] = value
        onChange()
    }

    fun <T : CSJsonObject> getJsonObjectMap(key: String, type: KClass<T>): Map<String, T>? =
        (data[key] as? Map<String, MutableMap<String, Any?>>)?.let { type.createJsonObjectMap(it) }

    fun <T : CSJsonObject> setJsonObjectMap(key: String, map: Map<String, T>?) {
        val value: Map<String, Map<String, Any?>>? = map?.let {
            buildMap(map.size) { map.forEach { (key, value) -> this[key] = value.data } }
        }
        if (data[key] == value) return
        data[key] = value
        onChange()
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

    override fun set(key: String, value: List<*>?) {
        if (value != null && data[key] == value) return
        data[key] = value?.toList()
        onChange()
    }

    override fun set(key: String, value: Map<String, *>?) {
        if (value != null && data[key] == value) return
        data[key] = value?.toMap()
        onChange()
    }

    override val jsonMap: Map<String, *> by lazy { data }

    override fun equals(other: Any?) =
        (other as? CSJsonObject)?.let { it.data == data } ?: super.equals(other)

    override fun hashCode() = data.hashCode()
}