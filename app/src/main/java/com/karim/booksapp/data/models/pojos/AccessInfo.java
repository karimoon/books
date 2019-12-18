
package com.karim.booksapp.data.models.pojos;

import com.squareup.moshi.Json;

public class AccessInfo {

    @Json(name = "country")
    public String country;
    @Json(name = "viewability")
    public String viewability;
    @Json(name = "embeddable")
    public Boolean embeddable;
    @Json(name = "publicDomain")
    public Boolean publicDomain;
    @Json(name = "textToSpeechPermission")
    public String textToSpeechPermission;
    @Json(name = "epub")
    public Epub epub;
    @Json(name = "pdf")
    public Pdf pdf;
    @Json(name = "webReaderLink")
    public String webReaderLink;
    @Json(name = "accessViewStatus")
    public String accessViewStatus;
    @Json(name = "quoteSharingAllowed")
    public Boolean quoteSharingAllowed;

}
