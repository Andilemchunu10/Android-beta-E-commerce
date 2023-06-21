package com.example.android_beta_e_commerce

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(private val con: Context, var list: List<ProductsItem>) :RecyclerView.Adapter<CartAdapter.ViewHolder>(){


    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var img = v.findViewById<ImageView>(R.id.cartImg)
        var name = v.findViewById<TextView>(R.id.cartProductName)
        var price = v.findViewById<TextView>(R.id.cartPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.cart_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position] // Get the ProductsItem at the current position
        Glide.with(con).load(product.image.imageURL).into(holder.img)
        holder.name.text = list[position].name
        holder.price.text = list[position].price.toString()

    }

}