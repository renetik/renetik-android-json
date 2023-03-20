package renetik.android.json

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import renetik.android.core.kotlin.collections.linkedMap
import renetik.android.core.kotlin.collections.list
import renetik.android.core.lang.catchWarnReturnNull

fun String.parseJsonMap(): MutableMap<String, Any?>? = parseJson<MutableMap<String, Any?>>()

fun String.parseJsonList(): MutableList<Any?>? = parseJson<MutableList<Any?>>()

// Here reified needs to stay! otherwise return type gets erased and if createValueFromJsonType
// returns different type we get ClassCastException..
@Suppress("UNCHECKED_CAST")
inline fun <reified Type> String.parseJson(): Type? =
    catchWarnReturnNull<Any, JSONException> {
        JSONTokener(this).nextValue()
    }.createValueFromJsonType() as? Type

fun Any?.createValueFromJsonType(): Any? {
    if (this is Number || this is String || this is Boolean) return this
    if (this is JSONObject) return createMapObject()
    if (this is JSONArray) return createListObject()
    return null
}

private fun JSONArray.createListObject(): List<Any?> {
    val list = list<Any?>()
    for (index in 0 until length()) list.add(this[index].createValueFromJsonType())
    return list
}

private fun JSONObject.createMapObject(): Map<String, Any?> {
    val map = linkedMap<String, Any?>()
    for (key in keys()) map[key] = this[key].createValueFromJsonType()
    return map
}