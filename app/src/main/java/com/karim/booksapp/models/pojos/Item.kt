package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class Item {

    @Json(name = "kind")
    var kind: String? = null
    @Json(name = "id")
    var id: String? = null
    @Json(name = "etag")
    var etag: String? = null
    @Json(name = "selfLink")
    var selfLink: String? = null
    @Json(name = "volumeInfo")
    var volumeInfo: VolumeInfo? = null
    @Json(name = "saleInfo")
    var saleInfo: SaleInfo? = null
    @Json(name = "accessInfo")
    var accessInfo: AccessInfo? = null
    @Json(name = "searchInfo")
    var searchInfo: SearchInfo? = null

}
