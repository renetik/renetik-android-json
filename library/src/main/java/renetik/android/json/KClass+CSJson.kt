package  renetik.android.json

import renetik.android.core.kotlin.collections.list
import renetik.android.core.kotlin.notNull
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

fun <T : CSJsonObject> KClass<T>.createJsonObject(map: Map<String, Any?>?) =
	createInstance().apply { map.notNull { load(it) } }

fun <T : CSJsonArray> KClass<T>.createJsonList(list: List<Any?>?) =
	createInstance().apply { list.notNull { load(it) } }

fun <T : CSJsonObject> KClass<T>.createJsonObjectList(data: List<Map<String, Any?>>?)
		: MutableList<T> = list<T>().also { dataList ->
	data?.forEach { dataList.put(createJsonObject(it)) }
}

fun <T : CSJsonObject> KClass<T>.createJsonObjectMap(data: Map<String, Map<String, Any?>>?)
		: MutableMap<String, T> = mutableMapOf<String, T>().also { dataList ->
	data?.forEach { dataList[it.key] = createJsonObject(it.value) }
}