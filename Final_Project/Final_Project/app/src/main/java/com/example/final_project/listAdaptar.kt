package com.example.final_project

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class listAdaptar {
    lateinit var context : ViewGroup
    class listAdaptar(var data: List<Fruit>, var act : Activity):RecyclerView.Adapter<listAdaptar.fruitHolder>(){
        class fruitHolder(val row: View):RecyclerView.ViewHolder(row){
            var dt: Fruit? = null
            val textView = row.findViewById<TextView>(R.id.textView2)
            val image = row.findViewById<ImageView>(R.id.imageView)

        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: fruitHolder, position: Int) {
            holder.dt = data.get(position)
            holder.textView.text = holder.dt!!.name
            Glide.with(act).load(holder.dt?.image_url).fitCenter()
            // to do : image'ı glide ile koy.
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fruitHolder {

            val layout = LayoutInflater.from(parent.context).inflate(R.layout.per_item,parent,false)
            val holder = fruitHolder(layout)
            holder.row.setOnClickListener{
                val intent = Intent(parent.context,Details::class.java)
                intent.putExtra("name" , holder.dt?.name)
                intent.putExtra("scientific_name" , holder.dt?.scientific_name)
                intent.putExtra("family" , holder.dt?.family)
                intent.putExtra("origin" , holder.dt?.origin)
                intent.putExtra("image_url" , holder.dt?.image_url)
                parent.context.startActivity(intent)
            }
            return holder
        }
    }
}