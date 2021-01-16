package com.example.App

import androidx.room.*

@Dao
interface DaoDB {
    //Inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    //Getters
    @Query("select * from author")
    suspend fun getauthors():List<Author>

    @Query("select * from author where id=:parid")
    suspend fun getauthorbyid(parid:Int): Author

    @Query("select * from book where fkauthor=:parid")
    suspend fun getbookbyfk(parid:Int):List<Book>

    //Updaters
    @Update
    suspend fun updateauthor(author: Author)

    @Update
    suspend fun updatebook(book: Book)

    //Delete
    @Query("delete from book where id=:parid")
    suspend fun deletebook(parid:Int)

    @Query("delete from author where id=:parid")
    suspend fun deleteauthor(parid: Int)

    @Query("select * from book where id=:parid")
    suspend fun getbookbyid(parid:Int):Book

}