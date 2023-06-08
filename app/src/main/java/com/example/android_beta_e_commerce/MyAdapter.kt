package com.example.android_beta_e_commerce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val con: Context, var list: List<ProductsItem>) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var img = v.findViewById<ImageView>(R.id.productImg)
        var name = v.findViewById<TextView>(R.id.productName)
        var price = v.findViewById<TextView>(R.id.productPrice)
        var add = v.findViewById<ImageView>(R.id.addIcon)
    }

    fun setFilteredList(list: List<ProductsItem>){
        this.list = list

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view = LayoutInflater.from(con).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position] // Get the ProductsItem at the current position

        Glide.with(con).load(product.image.imageURL).into(holder.img)

        holder.name.text =list[position].name
        holder.price.text =list[position].price.toString()

        // Set the addIcon image
        holder.add.setImageResource(R.drawable.add_icon)
    }

}