package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class AccessInfo {

    @Json(name = "country")
    var country: String? = null
    @Json(name = "viewability")
    var viewability: String? = null
    @Json(name = "embeddable")
    var embeddable: Boolean? = null
    @Json(name = "publicDomain")
    var publicDomain: Boolean? = null
    @Json(name = "textToSpeechPermission")
    var textToSpeechPermission: String? = null
    @Json(name = "epub")
    var epub: Epub? = null
    @Json(name = "pdf")
    var pdf: Pdf? = null
    @Json(name = "webReaderLink")
    var webReaderLink: String? = null
    @Json(name = "accessViewStatus")
    var accessViewStatus: String? = null
    @Json(name = "quoteSharingAllowed")
    var quoteSharingAllowed: Boolean? = null

}
