package renetik.android.json

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import renetik.android.json.obj.CSJsonObject
import renetik.android.json.obj.getBooleanMap
import renetik.android.json.obj.getDoubleList
import renetik.android.json.obj.load

data class SimpleJsonObject(
    var string: String? = null,
    var map: Map<String, Boolean>? = null,
    var list: List<Double>? = null) : CSJsonObject() {

    init {
        set("stringKey", string)
        set("mapKey", map)
        set("listKey", list)
    }

    override fun onLoaded() {
        string = get("stringKey")
        map = getBooleanMap("mapKey", default = false)
        list = getDoubleList("listKey", default = 0.0)
    }
}

@RunWith(RobolectricTestRunner::class)
class SimpleJsonObjectTest {
    private val exceptedJson: String = """{
  "stringKey": "testObject",
  "mapKey": {
    "key1": true,
    "key2": false
  },
  "listKey": [
    1.2,
    3.4,
    5
  ]
}"""

    @Test
    fun customJsonObjectSetGetTest2() {
        val instance = SimpleJsonObject("testObject",
            mapOf("key1" to true, "key2" to false),
            listOf(1.2, 3.4, 5.0))
        assertEquals(exceptedJson, instance.toJson(formatted = true))
        assertEquals(instance, SimpleJsonObject().load(exceptedJson))
    }
}
