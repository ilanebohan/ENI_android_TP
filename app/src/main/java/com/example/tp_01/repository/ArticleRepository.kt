package com.example.tp_01.repository

import com.example.tp_01.bo.Article
import com.example.tp_01.dao.ArticleDAO
import com.example.tp_01.dao.DAOFactory
import com.example.tp_01.dao.DAOType

class ArticleRepository () {

    companion object{
        val ArticleDao : ArticleDAO = DAOFactory.createArticleDAO(DAOType.MEMORY);

        fun getArticle(id : Long) : Article? {
            return ArticleDao.selectbyId(id);
        }

        fun getLastArticle() : Article? {
            return ArticleDao.selectLastOne();
        }

        fun addArticle(article : Article) : Long {
            return ArticleDao.addNewOne(article);
        }

        fun getAllArticles() : List<Article> {
            return ArticleDao.selectAll();
        }
    }


}