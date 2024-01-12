package com.example.tp_01.dao.memory

import com.example.tp_01.bo.Article
import com.example.tp_01.dao.ArticleDAO
import java.util.Date

class ArticleDAOMemoryImpl : ArticleDAO {

    companion object {
        val article1 = Article(1, "Titre1", "Description1", 10.0, "url1", Date());
        val article2 = Article(2, "Titre2", "Description2", 20.0, "url2", Date());
        val article3 = Article(3, "Titre3", "Description3", 30.0, "url3", Date());
        var articlesInMemory = mutableListOf(article1, article2, article3)
    }

    override fun selectbyId(id: Long): Article? {
       return articlesInMemory.find { it.id == id }
    }

    override fun addNewOne(article: Article): Long {
        articlesInMemory.add(article);
        return article.id;
    }

    override fun selectAll(): List<Article> {
        return articlesInMemory;
    }

}