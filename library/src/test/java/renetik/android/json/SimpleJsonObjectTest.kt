package renetik.android.json

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import renetik.android.json.obj.load

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
