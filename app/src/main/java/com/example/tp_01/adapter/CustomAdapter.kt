package com.example.tp_01.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_01.R
import com.example.tp_01.bo.Article
import com.example.tp_01.viewmodel.ArticlesViewModel

class CustomAdapter(private val maList: List<Article>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var onItemClick: ((Article) -> Unit)? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ArticlesViewModel = maList[position]

        // sets the text to the textview from our itemHolder class
        holder.tvnomArticle.text = ArticlesViewModel.titre
        holder.tvprixArticle.text = ArticlesViewModel.prix.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return maList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvnomArticle: TextView = itemView.findViewById(R.id.tv_nomArticle)
        val tvprixArticle: TextView = itemView.findViewById(R.id.tv_prixArticle)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(maList[bindingAdapterPosition])
            }
        }
    }


}