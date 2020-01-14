package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class Epub {

    @Json(name = "isAvailable")
    var isAvailable: Boolean? = null
    @Json(name = "acsTokenLink")
    var acsTokenLink: String? = null

}
