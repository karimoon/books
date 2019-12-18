
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class IndustryIdentifier {

    @Json(name = "type")
    public String type;
    @Json(name = "identifier")
    public String identifier;

}
