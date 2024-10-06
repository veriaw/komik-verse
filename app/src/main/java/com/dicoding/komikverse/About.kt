package com.dicoding.komikverse

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class About : AppCompatActivity(), View.OnClickListener {
    private lateinit var callBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.about_section)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        callBtn=findViewById(R.id.call_me)
        callBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.call_me->{
                val telpNum="0895320153112"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telpNum"))
                startActivity(dialPhoneIntent)
            }
        }
    }
}