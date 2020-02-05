package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class PanelizationSummary {

    @Json(name = "containsEpubBubbles")
    var containsEpubBubbles: Boolean? = null
    @Json(name = "containsImageBubbles")
    var containsImageBubbles: Boolean? = null

}
