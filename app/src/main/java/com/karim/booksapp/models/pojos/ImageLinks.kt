package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class ImageLinks {

    @Json(name = "smallThumbnail")
    var smallThumbnail: String? = null
    @Json(name = "thumbnail")
    var thumbnail: String? = null

}
