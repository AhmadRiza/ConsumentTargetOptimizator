package riza.com.cto

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val doubleCoordinate: Double = 112.61391687420308

        val floatCoordinate: Float = doubleCoordinate.toFloat()

        assertEquals(doubleCoordinate, floatCoordinate)

    }
}
