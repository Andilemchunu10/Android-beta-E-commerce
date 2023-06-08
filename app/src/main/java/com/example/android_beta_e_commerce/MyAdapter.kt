package com.example.android_beta_e_commerce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyAdapter(var con : Context, var list: List<ProductsItem>, ) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClickListener(position: Int)
    }

    fun setOnItemClicklistener(listener: onItemClickListener){
        mListener =listener
    }


    inner class ViewHolder(listener: onItemClickListener, v: View ):RecyclerView.ViewHolder(v){
        var img = v.findViewById<ImageView>(R.id.productImg)
        var name = v.findViewById<TextView>(R.id.productName)
        var price = v.findViewById<TextView>(R.id.productPrice)

        var add = v.findViewById<ImageView>(R.id.addIcon)


        init {
            v.setOnClickListener {
                listener.onItemClickListener(adapterPosition)
            }

        }

    }

    fun setFilteredList(list: List<ProductsItem>){
        this.list = list

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(con).inflate(R.layout.item,parent,false)
        return ViewHolder(mListener,view)
    }

    override fun getItemCount(): Int {

        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
<<<<<<< HEAD
        val product = list[position] // Get the ProductsItem at the current position

        Glide.with(con).load(product.image.imageURL).into(holder.img)

=======
<<<<<<< HEAD
        Glide.with(con).load(list[position].image ).into(holder.img)
=======
        val product = list[position] // Get the ProductsItem at the current position

        Glide.with(con).load(product.image.imageURL).into(holder.img)
>>>>>>> 5ce3390 (added the viewall with the backend api)

>>>>>>> ee3538f5a41f1f0d0c9c99bbdbea11d1d5e4267d
        holder.name.text =list[position].name
        holder.price.text =list[position].price.toString()

        // Set the addIcon image
        holder.add.setImageResource(R.drawable.add_icon)
    }


}