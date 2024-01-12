package com.example.tp_01.dao

import com.example.tp_01.dao.memory.ArticleDAOMemoryImpl
import com.example.tp_01.dao.network.ArticleDAONetworkImpl

class DAOFactory {
    companion object {
        fun createArticleDAO(type : DAOType): ArticleDAO {
            return when (type) {
                DAOType.MEMORY -> ArticleDAOMemoryImpl()
                DAOType.NETWORK -> ArticleDAONetworkImpl()
            }
        }
    }
}