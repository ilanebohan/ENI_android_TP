package com.example.tp_01.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tp_01.bo.Article


@Dao
interface ArticleDAO {

    @Insert
    fun addNewOne(article: Article) : Long

    @Query("SELECT * FROM Article WHERE id = :id")
    fun selectbyId(id : Long) : Article?

    @Update
    fun update(article: Article)

    @Delete
    fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun selectAll() : List<Article>
}