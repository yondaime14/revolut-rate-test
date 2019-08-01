package com.carllewis14.revoluttest.util.image

import android.widget.ImageView

interface ImageLoader {

    fun loadCountryFlag(imageView: ImageView, countryCode: String)

}