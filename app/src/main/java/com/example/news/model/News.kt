package com.example.news.model

import android.os.Parcel
import android.os.Parcelable

data class News(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var urlToImage: String? = null,
    var url: String? = null,
    var content: String? = null
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeString(urlToImage)
        dest.writeString(url)
        dest.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}

