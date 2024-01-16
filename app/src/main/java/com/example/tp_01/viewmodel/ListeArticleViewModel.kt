package com.example.tp_01.viewmodel

import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp_01.bo.Article
import com.example.tp_01.repository.ArticleRepository

class ListeArticleViewModel : ViewModel() {

    var ListeArticles = MutableLiveData(mutableListOf<Article>());

    fun getArticlesList() : MutableList<Article>?{
        ListeArticles.value = ArticleRepository.getAllArticles().toMutableList();

        return ListeArticles.value;
    }

    fun getLastArticle(): Article? {
        var lastArticle = ArticleRepository.getLastArticle();
        return lastArticle;
    }

    fun getRandomArticle() : Article? {
        var vretour : Article? = null;
        var lesArticles = ArticleRepository.getAllArticles();
        var size = lesArticles.size;
        if(size > 0){
            var random = (0..size).random();
            vretour =  lesArticles.get(random);
        }
        return vretour;
    }
}