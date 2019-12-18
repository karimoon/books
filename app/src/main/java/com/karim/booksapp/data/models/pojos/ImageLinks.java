
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class ImageLinks {

    @Json(name = "smallThumbnail")
    public String smallThumbnail;
    @Json(name = "thumbnail")
    public String thumbnail;

}
