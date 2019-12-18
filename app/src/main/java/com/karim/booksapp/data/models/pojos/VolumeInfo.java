
package com.karim.booksapp.data.models.pojos;

import java.util.List;
import com.squareup.moshi.Json;

public class VolumeInfo {

    @Json(name = "title")
    public String title;
    @Json(name = "authors")
    public List<String> authors = null;
    @Json(name = "publisher")
    public String publisher;
    @Json(name = "publishedDate")
    public String publishedDate;
    @Json(name = "description")
    public String description;
    @Json(name = "industryIdentifiers")
    public List<IndustryIdentifier> industryIdentifiers = null;
    @Json(name = "readingModes")
    public ReadingModes readingModes;
    @Json(name = "pageCount")
    public Integer pageCount;
    @Json(name = "printType")
    public String printType;
    @Json(name = "categories")
    public List<String> categories = null;
    @Json(name = "maturityRating")
    public String maturityRating;
    @Json(name = "allowAnonLogging")
    public Boolean allowAnonLogging;
    @Json(name = "contentVersion")
    public String contentVersion;
    @Json(name = "panelizationSummary")
    public PanelizationSummary panelizationSummary;
    @Json(name = "imageLinks")
    public ImageLinks imageLinks;
    @Json(name = "language")
    public String language;
    @Json(name = "previewLink")
    public String previewLink;
    @Json(name = "infoLink")
    public String infoLink;
    @Json(name = "canonicalVolumeLink")
    public String canonicalVolumeLink;
    @Json(name = "subtitle")
    public String subtitle;
    @Json(name = "averageRating")
    public Double averageRating;
    @Json(name = "ratingsCount")
    public Integer ratingsCount;

}
