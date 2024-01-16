package com.example.tp_01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.tp_01.bo.Article
import com.example.tp_01.repository.ArticleRepository
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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
class ListeArticleFragment : Fragment() {

    val vmListe by viewModels<ListeArticleViewModel>();



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lesArticles = vmListe.getArticlesList();

        if (lesArticles!!.isEmpty())
        {
            // AUCUN ARTICLE
        }
        else
        {
            for (article in lesArticles) {
                //listeNoms += article.titre + "\n";
                val tv = TextView(context);
                tv.text = article.titre;

                view?.findViewById<LinearLayout>(R.id.linearLayoutArticles)?.addView(tv);

                tv.setOnClickListener{
                    val intent = Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra("query", "eni-shop"+ article.titre);
                    startActivity(intent);
                }

            }

        }

        view.findViewById<Button>(R.id.btn_goToDetails).setOnClickListener {

            val rnds = (0..lesArticles.size).random() // generated random from 0 to 10 included
            val article = lesArticles[rnds];

            val direction = ListeArticleFragmentDirections.actionListeToDetails(article)
            Navigation.findNavController(view).navigate(direction)
        }
    }

}