package com.karim.booksapp.data.models.pojos


import com.squareup.moshi.Json

class Offer {

    @Json(name = "finskyOfferType")
    var finskyOfferType: Int? = null
    @Json(name = "listPrice")
    var listPrice: ListPrice_? = null
    @Json(name = "retailPrice")
    var retailPrice: RetailPrice_? = null
    @Json(name = "giftable")
    var giftable: Boolean? = null

}
