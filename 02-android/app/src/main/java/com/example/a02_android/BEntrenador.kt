package com.example.a02_android

import android.os.Parcel
import android.os.Parcelable

class BEntrenador(
    val name: String?,
    val desc: String?,
    val lig:liga?
    ):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(liga::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)//Definir como se ban a escribir las variebles
        parcel.writeString(desc)
        parcel.writeParcelable(lig,flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }


}
