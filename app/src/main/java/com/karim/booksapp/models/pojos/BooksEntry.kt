package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class BooksEntry {

    @Json(name = "kind")
    var kind: String? = null
    @Json(name = "totalItems")
    var totalItems: Int? = null
    @Json(name = "items")
    var items: List<Item>? = null

}
