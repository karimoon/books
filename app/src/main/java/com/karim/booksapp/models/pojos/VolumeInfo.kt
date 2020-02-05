package com.karim.booksapp.models.pojos

import com.squareup.moshi.Json

class VolumeInfo {

    @Json(name = "title")
    var title: String? = null
    @Json(name = "authors")
    var authors: List<String>? = null
    @Json(name = "publisher")
    var publisher: String? = null
    @Json(name = "publishedDate")
    var publishedDate: String? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "industryIdentifiers")
    var industryIdentifiers: List<IndustryIdentifier>? = null
    @Json(name = "readingModes")
    var readingModes: ReadingModes? = null
    @Json(name = "pageCount")
    var pageCount: Int? = null
    @Json(name = "printType")
    var printType: String? = null
    @Json(name = "categories")
    var categories: List<String>? = null
    @Json(name = "maturityRating")
    var maturityRating: String? = null
    @Json(name = "allowAnonLogging")
    var allowAnonLogging: Boolean? = null
    @Json(name = "contentVersion")
    var contentVersion: String? = null
    @Json(name = "panelizationSummary")
    var panelizationSummary: PanelizationSummary? = null
    @Json(name = "imageLinks")
    var imageLinks: ImageLinks? = null
    @Json(name = "language")
    var language: String? = null
    @Json(name = "previewLink")
    var previewLink: String? = null
    @Json(name = "infoLink")
    var infoLink: String? = null
    @Json(name = "canonicalVolumeLink")
    var canonicalVolumeLink: String? = null
    @Json(name = "subtitle")
    var subtitle: String? = null
    @Json(name = "averageRating")
    var averageRating: Double? = null
    @Json(name = "ratingsCount")
    var ratingsCount: Int? = null

}
