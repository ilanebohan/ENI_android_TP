package com.example.tp_01.dao

import com.example.tp_01.bo.Article

interface ArticleDAO {
    fun selectbyId(id: Long): Article?

    fun addNewOne(article: Article): Long

    fun selectAll(): List<Article>
}