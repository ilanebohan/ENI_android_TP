package com.example.tp_01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.tp_01.databinding.ActivityMainBinding
import com.example.tp_01.viewmodel.DetailArticleViewModel
import com.example.tp_01.viewmodel.ListeArticleViewModel
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG = "DetailArticleFragment";
class DetailArticleFragment : Fragment() {

    val args: DetailArticleFragmentArgs by navArgs()
    val vm by viewModels<DetailArticleViewModel>{DetailArticleViewModel.Factory};


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated - Details1: ${args.article.toString()}");

        vm.isArticleInDb(args.article).observe(viewLifecycleOwner) {
            if (it.equals(true)) {
                Log.i(TAG, "onViewCreated2: L'article ${args.article.id} est dans la BDD");
                view.findViewById<CheckBox>(R.id.chkBox_Favoris).isChecked = true;
            } else {
                Log.i(TAG, "onViewCreated2: L'article ${args.article.id} n'est pas dans la BDD");
                view.findViewById<CheckBox>(R.id.chkBox_Favoris).isChecked = false;
            }

        }



        // Affichage des détails de l'article
        view.findViewById<TextView>(R.id.tv_detailsArticle).text = args.article.toString();
        Log.i(TAG, "onViewCreated - Details2: ${view.findViewById<TextView>(R.id.tv_detailsArticle).text}");


        var i =0;
        // Eventlistener pour la checkbox "Favori"
        view.findViewById<CheckBox>(R.id.chkBox_Favoris).setOnCheckedChangeListener() { buttonView, isChecked ->
            if (i > 0)
            {
                if (isChecked) {
                    vm.addArticle(args.article);
                    // toast
                    Toast.makeText(
                        context,
                        "Ajout de l'article : ${args.article.titre}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else {
                    vm.deleteArticle(args.article);
                    //Toast
                    Toast.makeText(
                        context,
                        "Suppression de l'article : ${args.article.titre}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            i++;
        }
    }

}