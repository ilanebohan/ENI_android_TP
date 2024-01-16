package com.example.tp_01.dao.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tp_01.bo.Article
import com.example.tp_01.dao.ArticleDAO

class ArticleDAONetworkImpl : ArticleDAO {

    override fun addNewOne(article: Article): Long {
        TODO("Not yet implemented")
    }

    override fun selectbyId(id: Long): Article? {
        TODO("Not yet implemented")
    }

    override fun update(article: Article) {
        TODO("Not yet implemented")
    }

    override fun delete(article: Article) {
        TODO("Not yet implemented")
    }

    override fun selectAll(): List<Article> {
        TODO("Not yet implemented")
    }

}