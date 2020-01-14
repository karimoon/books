package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class SearchInfo {

    @Json(name = "textSnippet")
    var textSnippet: String? = null

}
