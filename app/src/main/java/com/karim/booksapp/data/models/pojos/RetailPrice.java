
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class RetailPrice {

    @Json(name = "amount")
    public Double amount;
    @Json(name = "currencyCode")
    public String currencyCode;

}
