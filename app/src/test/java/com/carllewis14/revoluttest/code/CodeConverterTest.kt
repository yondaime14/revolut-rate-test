package com.carllewis14.revoluttest.code

import com.carllewis14.revoluttest.util.code.CodeConverter
import com.carllewis14.revoluttest.util.code.CodeConverterImpl
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class CodeConverterTest{

    var SUT: CodeConverter? = null

    @Before
    fun setup(){
        SUT = CodeConverterImpl()
    }

    @Test
    fun convert_currencyCode_countryCodeInLowerCaseReturned() {
        var result = SUT?.convertCurrencyCodeToCountryCodeInLowercase("USD")
        assertThat(result, `is`("us"))
    }

}