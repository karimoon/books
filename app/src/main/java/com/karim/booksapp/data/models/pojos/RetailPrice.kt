package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class RetailPrice {

    @Json(name = "amount")
    var amount: Double? = null
    @Json(name = "currencyCode")
    var currencyCode: String? = null

}
