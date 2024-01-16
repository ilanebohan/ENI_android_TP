package com.example.tp_01.viewmodel

import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.tp_01.AppDatabase
import com.example.tp_01.bo.Article
import com.example.tp_01.dao.ArticleDAO
import com.example.tp_01.dao.DAOFactory
import com.example.tp_01.dao.DAOType
import com.example.tp_01.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListeArticleViewModel(private val articleDAO: ArticleDAO) : ViewModel() {

    var ListeArticles = MutableLiveData<List<Article>>();
    var articlesFromMemory = MutableLiveData<List<Article>>();


    fun getArticlesList(){
        viewModelScope.launch(Dispatchers.IO ) {
            ListeArticles.postValue(articleDAO.selectAll());
        }
        //articlesFromMemory.value = articleDAO.selectAllArticles();
        var daoMemory = DAOFactory.createArticleDAO(DAOType.MEMORY);
        articlesFromMemory.value = daoMemory.selectAll();
    }

    fun getRandomArticle() : Article? {
        var vretour : Article? = null;
        viewModelScope.launch(Dispatchers.IO) {
            var lesArticles = articleDAO.selectAll();
            var size = lesArticles.size;
            if(size > 0){
                var random = (0..size).random();
                vretour =  lesArticles.get(random);
            }
        }
        return vretour;
    }

    fun getLastArticle(): Article {
        var vretour : Article? = null;
        viewModelScope.launch(Dispatchers.IO) {
            var lesArticles = articleDAO.selectAll();
            var size = lesArticles.size;
            if(size > 0){
                vretour =  lesArticles.get(size-1);
            }
        }
        return vretour!!;

    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {


            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return ListeArticleViewModel(
                    AppDatabase.getInstance(application.applicationContext).articledao()
                ) as T
            }
        }
    }
}