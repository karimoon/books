
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class ReadingModes {

    @Json(name = "text")
    public Boolean text;
    @Json(name = "image")
    public Boolean image;

}
