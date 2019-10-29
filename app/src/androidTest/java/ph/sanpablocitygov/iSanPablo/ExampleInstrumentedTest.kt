package ph.sanpablocitygov.iSanPablo

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the home_event_pic1 under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.mis_pc_11.spc_app1", appContext.packageName)
    }
}
