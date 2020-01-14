package com.karim.booksapp.data.models.pojos

import com.squareup.moshi.Json

class PanelizationSummary {

    @Json(name = "containsEpubBubbles")
    var containsEpubBubbles: Boolean? = null
    @Json(name = "containsImageBubbles")
    var containsImageBubbles: Boolean? = null

}
