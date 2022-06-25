package renetik.android.json

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import renetik.android.core.logging.CSLog
import renetik.android.core.logging.CSLog.logInfo

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class CSJsonObjectTest {
	private val stringKey = "stringKey"
	private val stringValue = "stringValue"
	private val expectedStringEntry = """"$stringKey":"$stringValue""""

	private val mapKey = "mapKey"
	private val mapValue = mapOf("1" to 1, "2" to 2)
	private val expectedMapEntry = """"$mapKey":{"1":1,"2":2}"""

	private val arrayKey = "arrayKey"
	private val arrayValue = arrayOf("1", 2, 3)
	private val expectedArrayEntry = """"$arrayKey":["1",2,3]"""

	@Test
	fun jsonObjectToJsonString() {
		val jsonObject = CSJsonObject()
		jsonObject.set(stringKey, stringValue)
		jsonObject.set(mapKey, mapValue)
		jsonObject.set(arrayKey, arrayValue)
		val jsonString = jsonObject.toJsonString(formatted = false)
		logInfo(jsonString)
		assertTrue(jsonString.contains(expectedStringEntry))
		assertTrue(jsonString.contains(expectedMapEntry))
		assertTrue(jsonString.contains(expectedArrayEntry))
	}

	val jsonString = """{"mapKey":{"1":1,"2":2},"stringKey":"stringValue","arrayKey":["1",2,3]}"""

	@Test
	fun jsonStringToJsonObject() {
		val jsonObject = CSJsonObject()
		jsonObject.load(jsonString)
//		jsonObject.get
//		jsonObject.set("object1", TestObject(stringValue, array = arrayValue))
//		jsonObject.set("object2", TestObject(map = mapValue))
//		jsonObject.set("object3", TestObject(stringValue, mapValue))
//		val jsonString = jsonObject.toJsonString(formatted = false)
//		assertTrue(jsonString.contains(expectedStringEntry))
//		assertTrue(jsonString.contains(expectedMapEntry))
//		assertTrue(jsonString.contains(expectedArrayEntry))
	}
}