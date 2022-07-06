package renetik.android.json

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import renetik.android.json.obj.CSJsonObject
import renetik.android.json.obj.load

@RunWith(RobolectricTestRunner::class)
class ComplexCustomJsonObjectTest {
    @Test
    fun customJsonObjectSetGetTest2() {
        val testObject = TestObject("testObject",
            mapOf("mapKey1" to TestObject("mapTestObject1"),
                "mapKey2" to TestObject("mapTestObject2")),
            listOf(TestObject("listTestObject1"), TestObject("listTestObject2")))
        val json = testObject.toJson(formatted = true)
        Assert.assertEquals(expectedJson, json)
        val value = TestObject().load(json)
        Assert.assertEquals(testObject, value)
    }

    /**
     * Create custom types by extending CSJsonObject
     */
    data class TestObject(
        var string: String? = null,
        var map: Map<String, TestObject>? = null,
        var list: List<TestObject>? = null) : CSJsonObject() {

        /**
         *   Use 'fun set(key: String,...' functions to save values using your keys
         */
        init {
            string?.let { set("stringKey", it) }
            map?.let { set("mapKey", it) }
            list?.let { set("listKey", it) }
        }

        /**
         *   Use 'fun get...' functions to retrieve values
         */
        override fun onLoaded() {
            string = get("stringKey")
            map = getJsonObjectMap("mapKey", TestObject::class)
            list = getJsonObjectList("listKey", TestObject::class)
        }
    }

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
}
