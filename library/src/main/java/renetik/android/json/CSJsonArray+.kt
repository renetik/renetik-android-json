package renetik.android.json

import org.json.JSONArray

fun CSJsonArray.toJsonArray(): JSONArray = toJsonList().toJSONArray()
fun <T : CSJsonArray> T.load(data: String) = apply { load(data.parseJsonList()!!) }
internal fun <T : CSJsonArray> T.add(value: Any?) = apply { data.add(value) }
fun <T : CSJsonArray> T.clone(): T = this::class.createJsonList(toJson().parseJson())