package riza.com.cto

import org.junit.Assert.assertEquals
import org.junit.Test
import riza.com.cto.core.PolygonUtils

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun testDoubleVsFloat() {

        val doubleCoordinate: Double = 112.61391687420308

        val floatCoordinate: Float = doubleCoordinate.toFloat()

        assertEquals(doubleCoordinate, floatCoordinate)

    }

    @Test
    fun testDegree() {

        val input = (112.61391687420308 - 112.61391400000000)

        println("input = ${String.format("%.14f", input)}")

        val degree = PolygonUtils.degreeToMeter(input)

        assertEquals(0, degree)

    }

}
