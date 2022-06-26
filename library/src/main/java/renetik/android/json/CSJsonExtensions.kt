package renetik.android.json

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import renetik.android.core.kotlin.asString
import renetik.android.core.kotlin.collections.linkedMap
import renetik.android.core.kotlin.collections.list
import renetik.android.core.lang.catchWarnReturnNull

fun String.parseJsonMap(): MutableMap<String, Any?>? = parseJson<MutableMap<String, Any?>>()

fun String.parseJsonList(): MutableList<Any?>? = parseJson<MutableList<Any?>>()

@Suppress("UNCHECKED_CAST")
fun <Type> String.parseJson(): Type? = catchWarnReturnNull<Any, JSONException> {
	JSONTokener(this).nextValue()
}.createValueFromJsonType() as? Type

fun Any.toJson(forceString: Boolean = false, formatted: Boolean = false): String {
	val jsonType = toJsonType(forceString)
	if (formatted) {
		if (jsonType is JSONArray) return jsonType.toString(2)
		if (jsonType is JSONObject) return jsonType.toString(2)
	}
	return java.lang.String.valueOf(jsonType)
}

@Suppress("UNCHECKED_CAST")
private fun Any?.toJsonType(forceString: Boolean = false): Any? {
	if (this is JSONObject || this is JSONArray) return this
	if (forceString) {
		if (this is String) return this
	} else if (this is Number || this is String || this is Boolean) return this
	return (this as? Map<String, *>)?.toJSONObject()
		?: (this as? Array<*>)?.toJSONArray()
		?: (this as? List<*>)?.toJSONArray()
		?: (this as? CSJsonObjectInterface)?.toJsonMap()?.toJSONObject()
		?: (this as? CSJsonArrayInterface)?.toJsonList()?.toJSONArray()
		?: this?.asString
}

fun List<*>.toJSONArray(forceString: Boolean = false): JSONArray {
	val jsonArray = JSONArray()
	for (entry in this) jsonArray.put(entry.toJsonType(forceString))
	return jsonArray
}

fun Array<*>.toJSONArray(forceString: Boolean = false): JSONArray {
	val jsonArray = JSONArray()
	for (entry in this) jsonArray.put(entry.toJsonType(forceString))
	return jsonArray
}

fun Map<String, *>.toJSONObject(forceString: Boolean = false): JSONObject {
	val jsonObject = JSONObject()
	for (entry in entries)
		jsonObject.put(entry.key, entry.value.toJsonType(forceString))
	return jsonObject
}

private fun Any?.createValueFromJsonType(): Any? {
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