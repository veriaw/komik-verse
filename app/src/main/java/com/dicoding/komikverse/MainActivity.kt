package com.dicoding.komikverse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView

    private val list=ArrayList<Comic>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvHeroes=findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list ->{
                rvHeroes.layoutManager=LinearLayoutManager(this)
            }
            R.id.action_grid ->{
                rvHeroes.layoutManager = GridLayoutManager(this,2)
            }
            R.id.action_about ->{
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Comic> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataSinopsis = resources.getStringArray(R.array.data_sinopsis)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listComics = ArrayList<Comic>()
        for (i in dataName.indices){
            val comic = Comic(dataName[i],dataGenre[i],dataDescription[i],dataPhoto[i],dataSinopsis[i])
            listComics.add(comic)
        }
    return listComics
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listComicAdapter = ListComicAdapter(list)
        rvHeroes.adapter = listComicAdapter
    }


}