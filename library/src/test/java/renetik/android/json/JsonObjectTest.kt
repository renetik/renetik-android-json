package renetik.android.json

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import renetik.android.json.CSJson.forceString
import renetik.android.json.obj.*

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class JsonObjectTest {

	@Test
	fun jsonObjectStoreString() {
		val json = CSJsonObject().apply { set("key", "some string") }.toJson()
		assertEquals("""{"key":"some string"}""", json)
		val value = CSJsonObject(json).getString("key")
		assertEquals(value, "some string")
	}

	@Test
	fun jsonObjectStoreBooleanAsString() {
		forceString = true
		val json = CSJsonObject().apply { set("key", false) }.toJson()
		assertEquals("""{"key":"false"}""", json)
		val value = CSJsonObject(json).getBoolean("key")
		assertEquals(value, false)
	}

	@Test
	fun jsonObjectStoreBoolean() {
		forceString = false
		val json = CSJsonObject().apply { set("key", false) }.toJson()
		assertEquals("""{"key":false}""", json)
		val value = CSJsonObject(json).getBoolean("key")
		assertEquals(value, false)
	}

	@Test
	fun jsonObjectStoreInt() {
		forceString = true
		val json = CSJsonObject().apply { set("key", int = 345) }.toJson()
		assertEquals("""{"key":"345"}""", json)
		val value = CSJsonObject(json).getInt("key")
		assertEquals(value, 345)
	}

	@Test
	fun jsonObjectStoreDouble() {
		forceString = true
		val json = CSJsonObject().apply { set("key", 213131.131331) }.toJson()
		assertEquals("""{"key":"213131.131331"}""", json)
		val value = CSJsonObject(json).getDouble("key")
		assertEquals(value, 213131.131331)
	}

	@Test
	fun jsonObjectStoreListString() {
		val value = listOf("1", "2", "3")
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":["1","2","3"]}""", json)
		val jsonObject = CSJsonObject(json)
		assertEquals(value, jsonObject.getStringList("key"))
		assertEquals(listOf(1, 2, 3), jsonObject.getIntList("key"))
	}

	@Test
	fun jsonObjectStoreListFloat() {
		forceString = false
		val value: List<Float> = listOf(1f, 2.5f, 32349.89f)
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":[1,2.5,32349.89]}""", json)
		val jsonObject = CSJsonObject(json)
		assertEquals(value, jsonObject.getFloatList("key"))
		assertEquals(listOf("1", "2.5", "32349.89"), jsonObject.getStringList("key"))
	}

	@Test
	fun jsonObjectStoreListDoubleAsString() {
		forceString = true
		val value: List<Double> = listOf(1.0, 2.5, 32349.89)
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":["1.0","2.5","32349.89"]}""", json)
		val jsonObject = CSJsonObject(json)
		assertEquals(value, jsonObject.getDoubleList("key"))
		assertEquals(listOf("1.0", "2.5", "32349.89"), jsonObject.getStringList("key"))
	}

	@Test
	fun jsonObjectStoreListDouble() {
		forceString = false
		val value: List<Double> = listOf(1.0, 2.5, 32349.89)
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":[1,2.5,32349.89]}""", json)
		val jsonObject = CSJsonObject(json)
		assertEquals(value, jsonObject.getDoubleList("key"))
		assertEquals(listOf("1", "2.5", "32349.89"), jsonObject.getStringList("key"))
	}

	@Test
	fun jsonObjectStoreMapString() {
		forceString = false
		val value: Map<String, String> =
			mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":{"key1":"value1","key2":"value2","key3":"value3"}}""", json)
		val returnValue = CSJsonObject(json).getStringMap("key")
		assertEquals(value, returnValue)
	}

	@Test
	fun jsonObjectStoreIntMapToStringMap() {
		forceString = false
		val value: Map<String, Double> = mapOf("key1" to 1.2, "key2" to 2.3, "key3" to 3.4)
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":{"key1":1.2,"key2":2.3,"key3":3.4}}""", json)
		val jsonObject = CSJsonObject(json)
		assertEquals(mapOf("key1" to "1.2", "key2" to "2.3", "key3" to "3.4"),
			jsonObject.getStringMap("key"))
		assertEquals(mapOf("key1" to 1.2f, "key2" to 2.3f, "key3" to 3.4f),
			jsonObject.getFloatMap("key"))
	}

	@Test
	fun jsonObjectStoreIntMapForceString() {
		forceString = false
		val value: Map<String, Double> = mapOf("key1" to 1.2, "key2" to 2.3, "key3" to 3.4)
		val json = CSJsonObject().apply { set("key", value) }.toJson()
		assertEquals("""{"key":{"key1":1.2,"key2":2.3,"key3":3.4}}""", json)
		val jsonObject = CSJsonObject(json)
		assertEquals(mapOf("key1" to "1.2", "key2" to "2.3", "key3" to "3.4"),
			jsonObject.getStringMap("key"))
		assertEquals(mapOf("key1" to 1.2f, "key2" to 2.3f, "key3" to 3.4f),
			jsonObject.getFloatMap("key"))
	}
}