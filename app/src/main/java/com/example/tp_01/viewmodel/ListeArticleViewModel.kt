package com.example.tp_01.viewmodel

import android.util.Log
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
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.Date

private const val TAG = "ListeArticleViewModel";
class ListeArticleViewModel(private val articleDAO: ArticleDAO) : ViewModel() {

    var ListeArticles = MutableLiveData<List<Article>>();
    var articlesFromMemory = MutableLiveData<List<Article>>();

    var articlesFromAPI = MutableLiveData<List<Article>>();


    fun fetchArticlesFromAPI(): MutableLiveData<List<Article>> {
        val url = "https://fakestoreapi.com/products"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: ",)
                }

                override fun onResponse(call: Call, response: Response) {
                    //Log.i(TAG, "onResponse: ${response.body?.string()}")
                    response.body?.let {
                        articlesFromAPI.postValue(deserializeResponse(it.string()));
                    }
                }
            })
        }
        Log.i(TAG, "fetchArticlesFromAPI: avant RETURN " + articlesFromAPI.value.toString())
        return articlesFromAPI;
    }

    fun deserializeResponse(responseBody: String) : List<Article>{
        val arrayListMovies = arrayListOf<Article>()
        val arrayArticlesJSON: JSONArray = JSONArray(responseBody)
        Log.i(TAG, "deserializeResponse: ${arrayArticlesJSON}")
        //val arrayArticlesJSON = responseJSON.getJSONArray("description")
        for(articleJSONIndex in 0 until arrayArticlesJSON.length()){
            val articleJSON = arrayArticlesJSON[articleJSONIndex] as JSONObject
            arrayListMovies.add(
                Article(
                    articleJSON.getInt("id").toLong(),
                    articleJSON.getString("title"),
                    articleJSON.getString("description"),
                    articleJSON.getDouble("price"),
                    articleJSON.getString("image"),
                    Date(),
                ))
        }
        return arrayListMovies
    }

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