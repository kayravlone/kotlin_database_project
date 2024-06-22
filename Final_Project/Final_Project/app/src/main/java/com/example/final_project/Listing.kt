package com.example.final_project

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Listing : AppCompatActivity() {
    lateinit var rv : RecyclerView
    var theList : MutableList<Fruit> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rv = findViewById(R.id.rcv)
        rv.layoutManager = LinearLayoutManager(this)


        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl("https://my-json-server.typicode.com/berkeozenc/Androidlecture12/").build()

        val ser:Service = retrofit.create(Service::class.java)
        ser.getData().enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(
                call: Call<List<Fruit>>,
                response : Response<List<Fruit>>
            ){
                if(!response.isSuccessful){
                    return
                }
                val body = response.body()!!
                for(x in body){
                    val o = Fruit(x.name,x.scientific_name,x.family,x.origin,x.image_url)
                    theList?.add(o)
                }
                setAdapter()
            }

            override fun onFailure(call: Call<List<Fruit>>, t: Throwable) {
                Log.i("asdasd","asdasdas")
            }
        })


    }
    fun setAdapter(){
        if(theList!=null){
            rv.adapter = listAdaptar.listAdaptar(theList, this)

        }
    }
}