package com.example.myapp_release

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
        val article = intent.getParcelableExtra<Article>("DATA")
        val imgarticle = findViewById<ImageView>(R.id.img_item_photo)
        val namearticle = findViewById<TextView>(R.id.tv_item_name)
        val descarticle = findViewById<TextView>(R.id.tv_item_description)

        imgarticle.setImageResource(article?.photo!!)
        namearticle.text = article.name
        descarticle.text = article.description

        val btnshare: Button = findViewById(R.id.action_share)
        btnshare.setOnClickListener { // Intent to share text
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")
            val shareText = """
               Check out this article:
               ${article.name}
               ${article.description}
               
               """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Share article"))
        }
    }
}