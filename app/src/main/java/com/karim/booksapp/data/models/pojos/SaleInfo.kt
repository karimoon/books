package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class SaleInfo {

    @Json(name = "country")
    var country: String? = null
    @Json(name = "saleability")
    var saleability: String? = null
    @Json(name = "isEbook")
    var isEbook: Boolean? = null
    @Json(name = "listPrice")
    var listPrice: ListPrice? = null
    @Json(name = "retailPrice")
    var retailPrice: RetailPrice? = null
    @Json(name = "buyLink")
    var buyLink: String? = null
    @Json(name = "offers")
    var offers: List<Offer>? = null

}
