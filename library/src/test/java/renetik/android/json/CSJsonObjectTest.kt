package renetik.android.json

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.Config.NONE

@RunWith(RobolectricTestRunner::class)
@Config(manifest= NONE)
class CSJsonObjectTest {
	@Test
	fun createObject() {
		val jsonObject = CSJsonObject()
		jsonObject.set(stringKey, stringValue)
		jsonObject.set(mapKey, mapValue)
		jsonObject.set(arrayKey, arrayValue)
		val jsonString = jsonObject.toJsonString(formatted = false)
		assertTrue(jsonString.contains(expectedStringEntry))
		assertTrue(jsonString.contains(expectedMapEntry))
		assertTrue(jsonString.contains(expectedArrayEntry))
	}

	@Test
	fun createObject2() {
		val jsonObject = CSJsonObject()
		jsonObject.set("object1", TestObject(stringValue, array = arrayValue))
		jsonObject.set("object2", TestObject(map = mapValue))
		jsonObject.set("object3", TestObject(stringValue, mapValue))
		val jsonString = jsonObject.toJsonString(formatted = false)
		assertTrue(jsonString.contains(expectedStringEntry))
		assertTrue(jsonString.contains(expectedMapEntry))
		assertTrue(jsonString.contains(expectedArrayEntry))
	}

	val jsonObject1String =
		"""{"mapKey":{"1":1,"2":2},"stringKey":"stringValue","arrayKey":["1",2,3]}"""

	@Test
	fun loadObject1() {
		val jsonObject = CSJsonObject()
		jsonObject.load(jsonObject1String)
//		jsonObject.set("object1", TestObject(stringValue, array = arrayValue))
//		jsonObject.set("object2", TestObject(map = mapValue))
//		jsonObject.set("object3", TestObject(stringValue, mapValue))
//		val jsonString = jsonObject.toJsonString(formatted = false)
//		assertTrue(jsonString.contains(expectedStringEntry))
//		assertTrue(jsonString.contains(expectedMapEntry))
//		assertTrue(jsonString.contains(expectedArrayEntry))
	}
}

private val stringKey = "stringKey"
private val stringValue = "stringValue"
private val expectedStringEntry = """"$stringKey":"$stringValue""""

private val mapKey = "mapKey"
private val mapValue = mapOf("1" to 1, "2" to 2)
private val expectedMapEntry = """"$mapKey":{"1":1,"2":2}"""

private val arrayKey = "arrayKey"
private val arrayValue = arrayOf("1", 2, 3)
private val expectedArrayEntry = """"$arrayKey":["1",2,3]"""

private class TestObject(string: String? = null,
                         map: Map<String, *>? = null,
                         array: Array<*>? = null) : CSJsonObject() {
	init {
		set(stringKey, string)
		set(mapKey, map)
		set(arrayKey, array)
	}
}


