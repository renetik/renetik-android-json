package renetik.android.json.obj

fun <T : CSJsonObject> List<T>.clone(): List<T> = map { it.clone() }