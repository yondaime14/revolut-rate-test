package com.carllewis14.revoluttest.text

import com.carllewis14.revoluttest.util.text.TextUtil
import com.carllewis14.revoluttest.util.text.TextUtilImpl
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class TextUtilTest {

    var SUT: TextUtil? = null

    @Before
    fun setUp() {
        SUT = TextUtilImpl()
    }

    @Test
    fun format_wholeNumber_numberWithTwoZerosShouldBeReturned() {
        var result = SUT?.formatWithTwoDecimalPlaces(5.0)
        MatcherAssert.assertThat(result, CoreMatchers.`is`("5.00"))
    }

    @Test
    fun format_decimalNumber_decimalNumberWithRoundedDownToHundreds() {
        var result = SUT?.formatWithTwoDecimalPlaces(7.234)
        MatcherAssert.assertThat(result, CoreMatchers.`is`("7.23"))
    }

    @Test
    fun format_decimalNumber_decimalNumberWithRoundedUpToHundreds() {
        var result = SUT?.formatWithTwoDecimalPlaces(7.235)
        MatcherAssert.assertThat(result, CoreMatchers.`is`("7.24"))
    }

}

