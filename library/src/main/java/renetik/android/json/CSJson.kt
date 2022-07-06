package renetik.android.json

import renetik.android.core.lang.CSEnvironment.isDebug

object CSJson {
    var forceStringInJson = false
    var isJsonFormatted = isDebug
    fun disableJsonFormat() {
        isJsonFormatted = false
    }
}