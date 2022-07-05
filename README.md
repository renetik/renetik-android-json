<!---Header--->
[![Android CI](https://github.com/renetik/renetik-android-json/workflows/Android%20CI/badge.svg)
](https://github.com/renetik/renetik-android-json/actions/workflows/android.yml)
# Renetik Android Json

#### [https://github.com/renetik/renetik-android-json](https://github.com/renetik/renetik-android-json/) ➜ [Documentation](https://renetik.github.io/renetik-android-json/)

Framework to enjoy, improve and speed up your application development while writing readable code.
Used as library in many projects and improving it while developing new projects.
I am open for [Hire](https://renetik.github.io) or investment in my mobile app music production & perfromance project Renetik Instruments www.renetik.com.

```gradle
allprojects {
    repositories {
        maven { url 'https://github.com/renetik/maven-snapshot/raw/master/repository' } //for master-SNAPSHOT
        maven { url 'https://github.com/renetik/maven/raw/master/repository' } 
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.renetik.library:renetik-android-json:$renetik-android-verison'
}
```

## Examples from included unit tests:
```
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
			set("stringKey", string)
			set("mapKey", map)
			set("listKey", list)
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
  "mapKey": {
    "mapKey2": {
      "stringKey": "mapTestObject2"
    },
    "mapKey1": {
      "stringKey": "mapTestObject1"
    }
  },
  "stringKey": "testObject",
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
```
## You can force strings while store on to json conversion, also you can choose to have formatted text.
```
class JsonObjectForceStringClearTest {
	@Test
	fun jsonObjectStoreBooleanClear() {
		forceString = true
		val stringJsonObject = CSJsonObject().apply {
			set("key1", false)
			set("key2", "value2")
			set("key3", 1.3)
		}
		assertEquals("""{"key1":"false","key2":"value2","key3":"1.3"}""", stringJsonObject.toJson())
		
		forceString = false
		val jsonObject = CSJsonObject().apply {
			set("key1", false)
			set("key2", "value2")
			set("key3", 1.3)
		}
		assertEquals("""{"key1":false,"key2":"value2","key3":1.3}""", jsonObject.toJson())
		assertEquals("""{"key1":"false","key2":"value2","key3":"1.3"}""",
			jsonObject.toJson(forceString = true))
			
		assertEquals("""{
  "key1": false,
  "key2": "value2",
  "key3": 1.3
}""", jsonObject.toJson(formatted = true))

		jsonObject.clear("key2");jsonObject.clear("key3")
		assertEquals("""{"key1":false}""", jsonObject.toJson())
	}
}
```

## Renetik Android - Libraries
#### [https://github.com/renetik/renetik-android-core](https://github.com/renetik/renetik-android-core/) ➜ [Documentation](https://renetik.github.io/renetik-android-core/)
#### [https://github.com/renetik/renetik-android-json](https://github.com/renetik/renetik-android-json/) ➜ [Documentation](https://renetik.github.io/renetik-android-json/)
#### [https://github.com/renetik/renetik-android-event](https://github.com/renetik/renetik-android-event/) ➜ [Documentation](https://renetik.github.io/renetik-android-event/)
#### [https://github.com/renetik/renetik-android-store](https://github.com/renetik/renetik-android-store/) ➜ [Documentation](https://renetik.github.io/renetik-android-store/)
#### [https://github.com/renetik/renetik-android-preset](https://github.com/renetik/renetik-android-preset/) ➜ [Documentation](https://renetik.github.io/renetik-android-preset/)
#### [https://github.com/renetik/renetik-android-framework](https://github.com/renetik/renetik-android-framework/) ➜ [Documentation](https://renetik.github.io/renetik-android-framework/)
