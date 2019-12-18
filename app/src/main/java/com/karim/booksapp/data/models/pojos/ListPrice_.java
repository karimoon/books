
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class ListPrice_ {

    @Json(name = "amountInMicros")
    public Double amountInMicros;
    @Json(name = "currencyCode")
    public String currencyCode;

}
