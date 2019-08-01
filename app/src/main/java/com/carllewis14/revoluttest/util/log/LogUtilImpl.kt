package com.carllewis14.revoluttest.util.log

import android.util.Log
import com.carllewis14.revoluttest.util.Constants.LOG_TAG

class LogUtilImpl: LogUtil {

    override fun log(message: String) {
        Log.d(LOG_TAG, message)
    }

}