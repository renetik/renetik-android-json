package renetik.android.json

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import renetik.android.json.obj.load

@RunWith(RobolectricTestRunner::class)
class ComplexJsonObjectTest {
    private val expectedJson = """
{
  "listKey": [
    {
      "stringKey": "listTestObject1"
    },
    {
      "stringKey": "listTestObject2"
    }
  ],
  "mapKey": {
    "mapKey1": {
      "stringKey": "mapTestObject1"
    },
    "mapKey2": {
      "stringKey": "mapTestObject2"
    }
  },
  "stringKey": "testObject"
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
