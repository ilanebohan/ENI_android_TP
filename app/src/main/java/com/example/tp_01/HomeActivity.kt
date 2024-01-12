package com.example.tp_01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_01.bo.Article
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val b = intent.extras;
        if (b != null)
        {
            val article = b.getParcelable("article") as Article?
            if (article != null)
            {
                Snackbar.make(findViewById(android.R.id.content), "Vous venez de créer " + article.titre + " vendu pour un montant de " + article.prix.toString() + " €", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

        }

        findViewById<FloatingActionButton>(R.id.fAb_addArticle).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent);
        }

    }



}