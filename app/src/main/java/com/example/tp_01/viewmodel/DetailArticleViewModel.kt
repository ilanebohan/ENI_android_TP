package com.example.tp_01.viewmodel

import android.util.Log
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "DetailArticleViewModel"
class DetailArticleViewModel(private val articleDAO: ArticleDAO) : ViewModel() {


    var isFavorite = MutableLiveData(false);
    var articleAdded = MutableLiveData<Long>();


    fun addArticle(article : Article){
        viewModelScope.launch(Dispatchers.IO ) {
            articleAdded.postValue(articleDAO.addNewOne(article));
        }
        var daoMemory = DAOFactory.createArticleDAO(DAOType.MEMORY);
        daoMemory.delete(article);
    }

    fun isArticleInDb(article : Article) : Boolean
    {
        var vretour = false;
        Log.i(TAG, "--- isArticleInDb: On vérifie que l'article ${article.id} est dans la BDD ... ---");
        viewModelScope.launch(Dispatchers.IO ) {
            var articleInDb = articleDAO.selectbyId(article.id);
            Log.i(TAG, "--- isArticleInDb: Résultat du select : ${articleInDb.toString()} ---");
            if (articleInDb != null)
            {
                Log.i(TAG, "--- isArticleInDb: L'article ${article.id} est dans la BDD ! ---");
                //isFavorite.postValue(true);
                vretour = true;
            }
            else
            {
                Log.i(TAG, "--- isArticleInDb: L'article ${article.id} n'est pas dans la BDD ! ---");
                //isFavorite.postValue(false);
                vretour = false;
            }
        }
        return vretour;
    }


    fun deleteArticle(article : Article)
    {
        viewModelScope.launch(Dispatchers.IO ) {
            articleDAO.delete(article);
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {


            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return DetailArticleViewModel(
                    AppDatabase.getInstance(application.applicationContext).articledao()
                ) as T
            }
        }
    }
}