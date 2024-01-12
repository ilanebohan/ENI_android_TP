package com.example.tp_01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_01.bo.Article
import com.example.tp_01.databinding.ActivityMainBinding
import com.example.tp_01.repository.ArticleRepository
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lesArticles = ArticleRepository.getAllArticles();

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var article = Article();
        //var article = ArticleRepository.getArticle(1);
        binding.article = article;

        binding.buttonSave.setOnClickListener {
            var nouvelArticle = Article(lesArticles.size.toLong() +1, binding.edtTitre.text.toString(), binding.edtDescription.text.toString(), binding.edtPrix.text.toString().toDouble(), "" ,Date());
            ArticleRepository.addArticle(nouvelArticle);
            // get the date
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("article", nouvelArticle);
            this.startActivity(intent);
            // show new SnackBar

        }

    }
}

