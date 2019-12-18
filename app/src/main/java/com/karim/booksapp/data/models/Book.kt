package com.karim.booksapp.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*


@Entity(tableName = "books")
data class Book(@PrimaryKey(autoGenerate = false) var id : String, var title : String, var subTitle : String
                , @Ignore var authors : Array<String?>, var publisher : String,
                var publishedDate : String, var description : String, var averageRating : Double, var thumbnail : String ) : Parcelable

{


    @ColumnInfo(name = "authors")
    var authorsRoom = ""
        get() = authors.joinToString(", ")



    constructor() : this("", "", "",emptyArray<String?>(), "", "","", 0.0, "")

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArray()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(p0: Parcel?, p1: Int) {

        p0?.writeString(id)
        p0?.writeString(title)
        p0?.writeString(subTitle)
        p0?.writeStringArray(authors)
        p0?.writeString(publisher)
        p0?.writeString(publishedDate)
        p0?.writeString(description)
        p0?.writeDouble(averageRating)
        p0?.writeString(thumbnail)


    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}
