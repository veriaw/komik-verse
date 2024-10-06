package com.dicoding.komikverse

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.komikverse.databinding.ActivityComicDetailBinding

class ComicDetail : AppCompatActivity() {
    private lateinit var binding:ActivityComicDetailBinding
    companion object{
        const val EXTRA_COMIC = "comic"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityComicDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val comic = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Comic>(EXTRA_COMIC, Comic::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Comic>(EXTRA_COMIC)
        }
        if (comic != null) {
            binding.titleDetail.text=comic.name
            binding.genreDetail.text=comic.genre
            binding.descriptionDetail.text=comic.description
            binding.sinopsisDetail.text=comic.sinopsis

            Glide.with(this)
                .load(comic.photo)
                .into(binding.imageDetail)
            Log.d("Tes", "Received comic: ${comic.sinopsis}")
        }else{
            Log.d("Tes", "No comic received from Intent")
        }
    }
}