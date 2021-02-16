package com.example.App

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.App.Author

@Database(entities = arrayOf( Author::class, Book::class),version=1)
@TypeConverters(Converters::class)
abstract class LibraryDB :RoomDatabase(){

    abstract val LibraryDAO: DaoDB

    companion object{
        @Volatile
        private var INSTANCE: LibraryDB?=null

        fun getInstance(context: Context): LibraryDB {
            synchronized(this){
                return INSTANCE ?:Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDB::class.java,
                    "LibraryDB"
                ).build().also {
                    INSTANCE =it
                }

            }
        }
    }
}