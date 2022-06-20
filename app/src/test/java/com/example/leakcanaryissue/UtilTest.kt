package com.example.leakcanaryissue

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Assert.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class UtilTest {
    @Test
    fun `Validate integer multiplication`() {
        val util = Util()
        assertEquals(util.multiply(5, 10), 50)
    }
}
