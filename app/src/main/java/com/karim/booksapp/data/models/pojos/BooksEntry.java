
package com.karim.booksapp.data.models.pojos;

import java.util.List;
import com.squareup.moshi.Json;

public class BooksEntry {

    @Json(name = "kind")
    public String kind;
    @Json(name = "totalItems")
    public Integer totalItems;
    @Json(name = "items")
    public List<Item> items = null;

}
