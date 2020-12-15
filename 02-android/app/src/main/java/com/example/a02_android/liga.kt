package com.example.a02_android

import android.os.Parcel
import android.os.Parcelable

class liga(
    val nombre:String?,
    val descripcion:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<liga> {
        override fun createFromParcel(parcel: Parcel): liga {
            return liga(parcel)
        }

        override fun newArray(size: Int): Array<liga?> {
            return arrayOfNulls(size)
        }
    }

}