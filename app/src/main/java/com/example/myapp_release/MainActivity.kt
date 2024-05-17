package com.example.myapp_release

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    private lateinit var cnn: RecyclerView
    private val list = ArrayList<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cnn = findViewById(R.id.cnn)
        cnn.setHasFixedSize(true)

        list.addAll(getListArticle())
        showRecyclerList()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.BLACK
        }
    }

    private fun getListArticle(): ArrayList<Article> {
        val dataName = resources.getStringArray(R.array.data_article)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listArticle = ArrayList<Article>()
        for (i in dataName.indices) {
            val Article = Article(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArticle.add(Article)
        }
        return listArticle

    }
    private fun showRecyclerList() {
        cnn.layoutManager = LinearLayoutManager(this)
        val listArticleAdapter = ListArticleAdapter(list)
        cnn.adapter = listArticleAdapter

        listArticleAdapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                val intentToDetail = Intent(this@MainActivity, DetailArticle::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
    private fun showSelectedArticle(Article: Article) {
        Toast.makeText(this, "Kamu memilih " + Article.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, About::class.java)
                moveIntent.putExtra(About.EXTRA_NAME, "Zahrina Candrakanti")
                moveIntent.putExtra(About.EXTRA_EMAIL, "zahrinacandrakanti@gmail.com")
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}