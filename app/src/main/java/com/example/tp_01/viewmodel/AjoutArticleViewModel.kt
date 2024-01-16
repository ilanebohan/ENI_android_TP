package com.example.tp_01.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_01.bo.Article
import com.example.tp_01.dao.ArticleDAO
import com.example.tp_01.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

private const val TAG = "AjoutArticleViewModel"

class AjoutArticleViewModel(private val articleDAO: ArticleDAO ) : ViewModel() {

    var articleIDAdded = MutableLiveData<Long>();

    var id = MutableLiveData(0L);
    var titre = MutableLiveData("");
    var description = MutableLiveData("");
    var prix = MutableLiveData(0.0);
    var dateSortie = MutableLiveData(Date())
    var urlImage = MutableLiveData("");

    fun addArticle(): Article {
        var article = Article();
        viewModelScope.launch(Dispatchers.IO ) {
            article = Article(id.value!!, titre.value!!, description.value!!, prix.value!!, urlImage.value!!, dateSortie.value!!);
            articleIDAdded.postValue(articleDAO.addNewOne(article));
            Log.i(TAG, "addArticle: | ${article.toString()} |" + TAG);
        }


        return article;
    }


}