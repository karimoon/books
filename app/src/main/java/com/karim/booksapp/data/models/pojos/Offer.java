
package com.karim.booksapp.data.models.pojos;


import com.squareup.moshi.Json;

public class Offer {

    @Json(name = "finskyOfferType")
    public Integer finskyOfferType;
    @Json(name = "listPrice")
    public ListPrice_ listPrice;
    @Json(name = "retailPrice")
    public RetailPrice_ retailPrice;
    @Json(name = "giftable")
    public Boolean giftable;

}
