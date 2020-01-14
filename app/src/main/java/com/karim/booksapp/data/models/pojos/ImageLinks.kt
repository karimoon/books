package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class ImageLinks {

    @Json(name = "smallThumbnail")
    var smallThumbnail: String? = null
    @Json(name = "thumbnail")
    var thumbnail: String? = null

}
