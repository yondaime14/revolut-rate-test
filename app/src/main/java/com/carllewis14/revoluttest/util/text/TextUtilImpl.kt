package com.carllewis14.revoluttest.util.text

import java.text.DecimalFormat

class TextUtilImpl: TextUtil {

    override fun formatWithTwoDecimalPlaces(double: Double): String{
        val formatter = DecimalFormat("0.00")
        val result = formatter.format(double)
        return result
    }

}