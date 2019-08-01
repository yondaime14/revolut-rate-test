package com.carllewis14.revoluttest.util.code

class CodeConverterImpl : CodeConverter {

    override fun convertCurrencyCodeToCountryCodeInLowercase(currencyCode: String?): String? {
        if (currencyCode != null && currencyCode.length >= 2) {
            return currencyCode.substring(0, 2).toLowerCase()
        } else {
            return ""
        }
    }

}