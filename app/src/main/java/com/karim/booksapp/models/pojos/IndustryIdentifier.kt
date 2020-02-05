package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class IndustryIdentifier {

    @Json(name = "type")
    var type: String? = null
    @Json(name = "identifier")
    var identifier: String? = null

}
