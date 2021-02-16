package com.example.App

import androidx.room.*
import java.util.*

@Entity(
    tableName = "book"
)
class Book (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name="name")
    val name:String,

    @ColumnInfo(name = "pages")
    val pages:Int,

    @ColumnInfo(name = "puntuation")
    val puntuation:Float,

    @ColumnInfo(name = "available")
    val available:Boolean,

    @ColumnInfo(name="relsdate")
    val relsdate:String,

    val fkauthor:Int



)