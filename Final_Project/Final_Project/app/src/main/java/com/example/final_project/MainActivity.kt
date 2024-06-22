package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    var theList : MutableList<Fruit> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl("https://my-json-server.typicode.com/berkeozenc/Androidlecture12/").build()

        val ser:Service = retrofit.create(Service::class.java)
        ser.getData().enqueue(object : Callback<List<Fruit>>{
            override fun onResponse(
                call: Call<List<Fruit>>,
                response : Response<List<Fruit>>
            ){
                Log.i("show","1")

                if(!response.isSuccessful){
                  return
              }
                Log.i("show","2")

                val body = response.body()!!
                Log.i("show","3")
                for(x in body){
                    val o = Fruit(x.name,x.scientific_name,x.family,x.origin,x.image_url)
                    theList?.add(o)
                }
                Log.i("show",theList.size.toString())
            }

            override fun onFailure(call: Call<List<Fruit>>, t: Throwable) {
                    Log.i("asdasd","sadasd")
            }
        })
        findViewById<Button>(R.id.button).setOnClickListener{
            val intent = Intent(this, Listing::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button2).setOnClickListener{

            var fruit : Fruit? = null
            for (x in theList){
                if(findViewById<EditText>(R.id.editTextText).text.equals(x.name)){
                    fruit = x
                    break
                }
            }
            if(fruit!= null){
                val intent = Intent(this, Details::class.java)
                intent.putExtra("name" , fruit.name)
                intent.putExtra("scientific_name" , fruit.scientific_name)
                intent.putExtra("family" , fruit.family)
                intent.putExtra("origin" , fruit.origin)
                intent.putExtra("image_url" , fruit.image_url)
                startActivity(intent)
            }
        }
    }
}