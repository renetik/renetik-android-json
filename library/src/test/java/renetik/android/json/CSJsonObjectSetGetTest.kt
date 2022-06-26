package renetik.android.json

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CSJsonObjectSetGetTest {

	@Test
	fun jsonObjectStoreString() {
		val jsonObject = CSJsonObject()
		jsonObject.set("key", "some string")
		val json = jsonObject.toJson()
		assertEquals("""{"key":"some string"}""", json)
		val value = CSJsonObject().apply { load(json) }.getString("key")
		assertEquals(value, "some string")
	}

	@Test
	fun jsonObjectStoreBoolean() {
		val jsonObject = CSJsonObject()
		jsonObject.set("key", false)
		val json = jsonObject.toJson()
		assertEquals("""{"key":"false"}""", json)
		val value = CSJsonObject().apply { load(json) }.getBoolean("key")
		assertEquals(value, false)
	}

	@Test
	fun jsonObjectStoreInt() {
		val jsonObject = CSJsonObject()
		jsonObject.set("key", int = 345)
		val json = jsonObject.toJson()
		assertEquals("""{"key":"345"}""", json)
		val value = CSJsonObject().apply { load(json) }.getInt("key")
		assertEquals(value, 345)
	}

	@Test
	fun jsonObjectStoreDouble() {
		val jsonObject = CSJsonObject()
		jsonObject.set("key", 213131.131331)
		val json = jsonObject.toJson()
		assertEquals("""{"key":"213131.131331"}""", json)
		val value = CSJsonObject().apply { load(json) }.getDouble("key")
		assertEquals(value, 213131.131331)
	}

	@Test
	fun jsonObjectStoreListString() {
		val jsonObject = CSJsonObject()
		jsonObject.set("key", listOf("1", "2", "3"))
		val json = jsonObject.toJson()
		assertEquals("""{"key":["1","2","3"]}""", json)
		val value = CSJsonObject().apply { load(json) }.getList("key")
		assertEquals(value, listOf("1", "2", "3"))
	}

	@Test
	fun jsonObjectStoreListFloat() {
		val jsonObject = CSJsonObject()
		val value: List<Float> = listOf(1f, 2.5f, 3234909.89f)
		jsonObject.set("key", value)
		val json = jsonObject.toJson()
//		assertEquals("""{"key":["1","2.5","3"]}""", json)
//		val returnValue = CSJsonObject().apply { load(json) }.getList("key") as? List<Float>
//		Assert.assertArrayEquals(returnValue?.toTypedArray(), listOf(1f, 2.5f, 3234909.89f).toTypedArray())
	}


}