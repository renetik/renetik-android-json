package renetik.android.json

import renetik.android.json.obj.CSJsonObject

data class ComplexJsonObject(
    var string: String? = null,
    var map: Map<String, ComplexJsonObject>? = null,
    var list: List<ComplexJsonObject>? = null) : CSJsonObject() {

    init {
        string?.let { set("stringKey", it) }
        map?.let { set("mapKey", it) }
        list?.let { set("listKey", it) }
    }

    override fun onLoaded() {
        string = get("stringKey")
        map = getJsonObjectMap("mapKey", ComplexJsonObject::class)
        list = getJsonObjectList("listKey", ComplexJsonObject::class)
    }
}