package renetik.android.json

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import renetik.android.json.obj.load

@RunWith(RobolectricTestRunner::class)
class JsonObjectEqualsTest {

    @Test
    fun jsonObjectStoreString() {
        val instance1 = ComplexJsonObject("testObject",
            list = listOf(ComplexJsonObject("testObjectListObject1", list =
            listOf(ComplexJsonObject("testObjectListObject1ListObject1"),
                ComplexJsonObject("testObjectListObject1ListObject2")))))

        val instance2 = ComplexJsonObject("testObject",
            list = listOf(ComplexJsonObject("testObjectListObject1", list =
            listOf(ComplexJsonObject("testObjectListObject1ListObject1"),
                ComplexJsonObject("testObjectListObject1ListObject2")))))

        assertEquals(instance1, instance2)

        val instance2FromString = ComplexJsonObject().load(instance2.toJson())
        assertEquals(instance1, instance2FromString)
    }

}