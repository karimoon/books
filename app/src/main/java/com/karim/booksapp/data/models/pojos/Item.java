
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class Item {

    @Json(name = "kind")
    public String kind;
    @Json(name = "id")
    public String id;
    @Json(name = "etag")
    public String etag;
    @Json(name = "selfLink")
    public String selfLink;
    @Json(name = "volumeInfo")
    public VolumeInfo volumeInfo;
    @Json(name = "saleInfo")
    public SaleInfo saleInfo;
    @Json(name = "accessInfo")
    public AccessInfo accessInfo;
    @Json(name = "searchInfo")
    public SearchInfo searchInfo;

}
