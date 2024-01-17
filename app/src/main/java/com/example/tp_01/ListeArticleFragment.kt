package com.example.tp_01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_01.adapter.CustomAdapter
import com.example.tp_01.bo.Article
import com.example.tp_01.viewmodel.ArticlesViewModel
import com.example.tp_01.viewmodel.ListeArticleViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListeArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


private const val TAG = "ListeArticleFragment";
class ListeArticleFragment : Fragment() {

    val vmListe by viewModels<ListeArticleViewModel> {ListeArticleViewModel.Factory};



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmListe.getArticlesList();

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerViewArticles)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        var data = vmListe.articlesFromMemory.value?.toList();

        // This will pass the ArrayList to our Adapter
        val adapter = data?.let { CustomAdapter(it) }

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


        view.findViewById<Button>(R.id.btn_articlesFav).setOnClickListener {
            /*for (article in vmListe.ListeArticles.value!!) {
                data.add(article)
            }*/

            data = data?.toMutableList();
            data as MutableList<Article> += vmListe.ListeArticles.value!!;


        }

        // set onClickListener for recyclerview items
        if (adapter != null) {
            adapter.onItemClick = { article ->
                Log.i(TAG, "onViewCreated: " + article.toString());
                val direction = ListeArticleFragmentDirections.actionListeToDetails(article)
                Navigation.findNavController(view).navigate(direction)
            }
        }


    }

}