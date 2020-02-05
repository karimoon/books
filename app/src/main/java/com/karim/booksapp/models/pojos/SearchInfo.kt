package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class SearchInfo {

    @Json(name = "textSnippet")
    var textSnippet: String? = null

}
