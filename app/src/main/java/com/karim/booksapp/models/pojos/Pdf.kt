package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class Pdf {

    @Json(name = "isAvailable")
    var isAvailable: Boolean? = null
    @Json(name = "acsTokenLink")
    var acsTokenLink: String? = null

}
