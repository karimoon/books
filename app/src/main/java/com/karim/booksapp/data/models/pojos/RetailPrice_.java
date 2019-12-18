
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class RetailPrice_ {

    @Json(name = "amountInMicros")
    public Double amountInMicros;
    @Json(name = "currencyCode")
    public String currencyCode;

}
