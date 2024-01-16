package com.example.tp_01

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tp_01.bo.Article
import com.example.tp_01.databinding.ActivityMainBinding
import com.example.tp_01.repository.ArticleRepository
import com.example.tp_01.viewmodel.AjoutArticleViewModel
import com.example.tp_01.viewmodel.ListeArticleViewModel
import java.util.Date

class MainActivity : AppCompatActivity() {

    val vm by viewModels<AjoutArticleViewModel>();
    val vmListe by viewModels<ListeArticleViewModel>();
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.vm = vm;
        binding.lifecycleOwner = this;

        var lastArticleAdded = vmListe.getLastArticle();
        binding.buttonSave.setOnClickListener {
            vm.id.value = lastArticleAdded?.id?.plus(1);
            var articleAdded = vm.addArticle();
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("article", articleAdded);
            this.startActivity(intent);
        }

    }
}

