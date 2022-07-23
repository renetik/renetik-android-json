package renetik.android.json

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import renetik.android.json.obj.CSJsonObject
import renetik.android.json.obj.load

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

@RunWith(RobolectricTestRunner::class)
class ComplexJsonObjectTest {
    private val expectedJson = """
{
  "stringKey": "testObject",
  "mapKey": {
    "mapKey1": {
      "stringKey": "mapTestObject1"
    },
    "mapKey2": {
      "stringKey": "mapTestObject2"
    }
  },
  "listKey": [
    {
      "stringKey": "listTestObject1"
    },
    {
      "stringKey": "listTestObject2"
    }
  ]
}""".trimStart()

    @Test
    fun customJsonObjectSetGetTest2() {
        val instance = ComplexJsonObject("testObject",
            mapOf("mapKey1" to ComplexJsonObject("mapTestObject1"),
                "mapKey2" to ComplexJsonObject("mapTestObject2")),
            listOf(ComplexJsonObject("listTestObject1"),
                ComplexJsonObject("listTestObject2")))

        Assert.assertEquals(expectedJson, instance.toJson(formatted = true))
        Assert.assertEquals(instance, ComplexJsonObject().load(expectedJson))
    }
}
