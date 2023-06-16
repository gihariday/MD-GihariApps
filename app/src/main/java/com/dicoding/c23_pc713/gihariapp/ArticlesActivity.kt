package com.dicoding.c23_pc713.gihariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.c23_pc713.gihariapp.databinding.ActivityArticlesBinding

class ArticlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticlesBinding
    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set onClickListener for back button
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        rvArticles = findViewById(R.id.rv_articles)
        rvArticles.setHasFixedSize(true)

        list.addAll(getListArticles())
        showRecyclerList()
    }

    private fun getListArticles(): ArrayList<Article> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listArticle = ArrayList<Article>()
        for (i in dataName.indices) {
            val article = Article(dataName[i], dataPhoto.getResourceId(i, -1))
            listArticle.add(article)
        }
        return listArticle
    }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListArticleAdapter(list)
        rvArticles.adapter = listHeroAdapter
    }
}