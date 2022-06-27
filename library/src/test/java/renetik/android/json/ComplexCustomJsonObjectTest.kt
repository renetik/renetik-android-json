package renetik.android.json

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ComplexCustomJsonObjectTest {

	data class TestObject(
		var string: String? = null,
		var map: Map<String, TestObject>? = null,
		var list: List<TestObject>? = null) : CSJsonObject() {

		init {
			set("stringKey", string)
			set("mapKey", map)
			set("listKey", list)
		}

		override fun onLoaded() {
			string = get("stringKey")
			map = getJsonObjectMap("mapKey", TestObject::class)
			list = getJsonObjectList("listKey", TestObject::class)
		}
	}

	private val testObjectInstance = TestObject("testObject",
		mapOf("key1" to TestObject("testObject2"), "key2" to TestObject("testObject2")),
		listOf(TestObject("testObject4"), TestObject("testObject5")))


	private val exceptedJson = """{
  "mapKey": {
    "key1": {
      "stringKey": "testObject2"
    },
    "key2": {
      "stringKey": "testObject2"
    }
  },
  "stringKey": "testObject",
  "listKey": [
    {
      "stringKey": "testObject4"
    },
    {
      "stringKey": "testObject5"
    }
  ]
}"""

	@Test
	fun customJsonObjectSetGetTest2() {
		val json = testObjectInstance.toJson(formatted = true)
		assertEquals(exceptedJson, json)
		val value = TestObject().load(json)
		assertEquals(testObjectInstance, value)
	}
}