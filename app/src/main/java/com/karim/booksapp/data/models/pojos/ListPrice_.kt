package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class ListPrice_ {

    @Json(name = "amountInMicros")
    var amountInMicros: Double? = null
    @Json(name = "currencyCode")
    var currencyCode: String? = null

}
