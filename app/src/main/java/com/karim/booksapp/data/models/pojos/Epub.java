
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class Epub {

    @Json(name = "isAvailable")
    public Boolean isAvailable;
    @Json(name = "acsTokenLink")
    public String acsTokenLink;

}
