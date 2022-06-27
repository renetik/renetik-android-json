package renetik.android.json

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CustomJsonObjectTest {

	data class TestObject(
		var string: String? = null,
		var map: Map<String, Boolean>? = null,
		var list: List<Double>? = null) : CSJsonObject() {

		init {
			set("stringKey", string)
			set("mapKey", map)
			set("arrayKey", list)
		}

		override fun onLoaded() {
			string = get("stringKey")
			map = getBooleanMap("mapKey", default = false)
			list = getDoubleList("arrayKey", default = 0.0)
		}
	}

	val testObjectInstance = TestObject("testObject",
		mapOf("key1" to true, "key2" to false),
		listOf(1.2, 3.4, 5.0))

	private val exceptedJson = """{
  "key": {
    "mapKey": {
      "key1": true,
      "key2": false
    },
    "stringKey": "testObject",
    "arrayKey": [
      1.2,
      3.4,
      5
    ]
  }
}"""

	@Test
	fun customJsonObjectSetGetTest() {
		val json = CSJsonObject().apply { set("key", testObjectInstance) }.toJson(formatted = true)
		assertEquals(exceptedJson, json)
		val value = CSJsonObject().apply { load(json) }.getJsonObject("key", TestObject::class)
		assertEquals(testObjectInstance, value)
	}

	private val exceptedJson2 = """{
  "mapKey": {
    "key1": true,
    "key2": false
  },
  "stringKey": "testObject",
  "arrayKey": [
    1.2,
    3.4,
    5
  ]
}"""

	@Test
	fun customJsonObjectSetGetTest2() {
		val json = testObjectInstance.toJson(formatted = true)
		assertEquals(exceptedJson2, json)
		val value = TestObject().apply { load(json) }
		assertEquals(testObjectInstance, value)
	}
}