package com.example.tp_01.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp_01.bo.Article
import com.example.tp_01.repository.ArticleRepository
import java.util.Date

private const val TAG = "AjoutArticleViewModel"

class AjoutArticleViewModel : ViewModel() {


    var id = MutableLiveData(0L);
    var titre = MutableLiveData("");
    var description = MutableLiveData("");
    var prix = MutableLiveData(0.0);
    var dateSortie = MutableLiveData(Date())
    var urlImage = MutableLiveData("");

    fun addArticle() : Article {

        var article = Article(id.value!!, titre.value!!, description.value!!, prix.value!!, urlImage.value!!, dateSortie.value!!);
        ArticleRepository.addArticle(article);

        Log.i(TAG, "addArticle: | ${article.toString()} |" + TAG);
        return article;
    }


}