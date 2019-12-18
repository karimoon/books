
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class PanelizationSummary {

    @Json(name = "containsEpubBubbles")
    public Boolean containsEpubBubbles;
    @Json(name = "containsImageBubbles")
    public Boolean containsImageBubbles;

}
