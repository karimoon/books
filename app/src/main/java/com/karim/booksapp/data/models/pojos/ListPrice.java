
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class ListPrice {

    @Json(name = "amount")
    public Double amount;
    @Json(name = "currencyCode")
    public String currencyCode;

}
