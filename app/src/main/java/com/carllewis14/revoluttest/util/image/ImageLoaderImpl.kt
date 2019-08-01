package com.carllewis14.revoluttest.util.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.carllewis14.revoluttest.util.Constants

class ImageLoaderImpl(var context: Context): ImageLoader {

    override fun loadCountryFlag(imageView: ImageView, countryCode: String) {
        var url = Constants.FLAGS_URL + countryCode + "/shiny" + "/64" + ".png"
        Glide.with(context).load(url).into(imageView)
    }

}