package com.example.final_project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var getName = intent.getStringExtra("name")
        var getscientific_name = intent.getStringExtra("scientific_name")
        var getFamily = intent.getStringExtra("family")
        var getOrigin = intent.getStringExtra("origin")
        var getImage_Url = intent.getStringExtra("image_url")
        Glide.with(this).load(getImage_Url).fitCenter().into(findViewById<ImageView>(R.id.imageView2))
        findViewById<TextView>(R.id.textView3).setText(getName)
        findViewById<TextView>(R.id.textView4).setText(getscientific_name)
        findViewById<TextView>(R.id.textView5).setText(getOrigin)
    }
}