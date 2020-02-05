package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class ReadingModes {

    @Json(name = "text")
    var text: Boolean? = null
    @Json(name = "image")
    var image: Boolean? = null

}
