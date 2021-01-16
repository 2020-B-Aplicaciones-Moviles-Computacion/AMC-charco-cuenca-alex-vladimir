package com.example.App

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "author"
)
class Author (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name="name")
    val name:String,

    @ColumnInfo(name = "age")
    val age:Int,

    @ColumnInfo(name = "weigth")
    val weigth:Float,

    @ColumnInfo(name = "alive")
    val alive:Boolean,

    @ColumnInfo(name="borndate")
    val borndate:String

)