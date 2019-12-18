
package com.karim.booksapp.data.models.pojos;

import java.util.List;
import com.squareup.moshi.Json;

public class SaleInfo {

    @Json(name = "country")
    public String country;
    @Json(name = "saleability")
    public String saleability;
    @Json(name = "isEbook")
    public Boolean isEbook;
    @Json(name = "listPrice")
    public ListPrice listPrice;
    @Json(name = "retailPrice")
    public RetailPrice retailPrice;
    @Json(name = "buyLink")
    public String buyLink;
    @Json(name = "offers")
    public List<Offer> offers = null;

}
