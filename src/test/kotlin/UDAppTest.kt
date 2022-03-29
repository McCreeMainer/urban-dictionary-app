import com.mzet.uda.UDApp
import com.mzet.uda.rapidapiKey
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class UDAppTest {

    private val app = UDApp().apply {
        changeKey("afd8a1edfemsh42e00d1360b74a0p1f7cc4jsnfddab1c1909f")
    }

    @Test
    fun `Test rapidapi key changing`() {
        val testKey = "aaaaaaaaaaaaaa"
        app.changeKey(testKey)
        assertEquals(rapidapiKey, testKey)
    }

    @Test
    fun `Test dict with multiple definitions`() {
        app.changeKey("afd8a1edfemsh42e00d1360b74a0p1f7cc4jsnfddab1c1909f")
        assertTrue("""Found \d+ definitions""".toRegex().matches(app.dict("amogus", null)))
    }

    @Test
    fun `Test dict bad definition index`() {
        app.changeKey("afd8a1edfemsh42e00d1360b74a0p1f7cc4jsnfddab1c1909f")
        try {
            app.dict("amogus", "1")
            app.dict("amogus", "-1")
            app.dict("amogus", "10000")
            app.dict("amogus", "-10000")
            app.dict("amogus", "")
            app.dict("amogus", "aaaaaaaaaaaaaaaa")
        } catch (e: Throwable) {
            fail()
        }
    }

    @Test
    fun `Test multiple random definitions`() {
        repeat(10) {
            assertNotEquals(app.random(), app.random())
        }
    }

    @Test
    fun `Test not existing dict`() {
        assertEquals("No definitions found", app.dict("askdjalkvbvldksjfbvlksdbvsndkvfsdblkvf", null))
    }

    @Test
    fun `Test null params`() {
        try {
            app.dict("a", "")
            app.dict("", "1")
            app.dict(null, "1")
            app.dict(null, "")
            app.dict("a", null)
            app.dict("", null)
            app.dict(null, null)
        } catch (e: Throwable) {
            fail()
        }
    }
}