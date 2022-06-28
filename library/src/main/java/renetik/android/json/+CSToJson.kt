package renetik.android.json

import org.json.JSONArray
import org.json.JSONObject
import renetik.android.core.kotlin.asString
import renetik.android.json.obj.CSJsonObjectInterface
import renetik.android.json.array.CSJsonArrayInterface

fun Any.toJson(forceString: Boolean = false, formatted: Boolean = CSJson.forceString): String {
	val jsonType = toJsonType(forceString)
	if (formatted) {
		if (jsonType is JSONArray) return jsonType.toString(2)
		if (jsonType is JSONObject) return jsonType.toString(2)
	}
	return java.lang.String.valueOf(jsonType)
}

@Suppress("UNCHECKED_CAST")
private fun Any?.toJsonType(forceString: Boolean = CSJson.forceString): Any? {
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

fun List<*>.toJSONArray(forceString: Boolean = CSJson.forceString): JSONArray {
	val jsonArray = JSONArray()
	for (entry in this) jsonArray.put(entry.toJsonType(forceString))
	return jsonArray
}

fun Array<*>.toJSONArray(forceString: Boolean = CSJson.forceString): JSONArray {
	val jsonArray = JSONArray()
	for (entry in this) jsonArray.put(entry.toJsonType(forceString))
	return jsonArray
}

fun Map<String, *>.toJSONObject(forceString: Boolean = CSJson.forceString): JSONObject {
	val jsonObject = JSONObject()
	for (entry in entries)
		jsonObject.put(entry.key, entry.value.toJsonType(forceString))
	return jsonObject
}