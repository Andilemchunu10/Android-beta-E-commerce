package com.example.android_beta_e_commerce

import android.content.Context
import android.content.Intent
<<<<<<< HEAD
import android.icu.text.Transliterator.Position
=======
>>>>>>> a148e7b785ed35a19780ccfd9ed4b6937950f589
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val con: Context, var list: List<ProductsItem>) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClickListener(position: Int)
        fun onBindViewHolder(holder: ViewHolder, position: Int)
    }

    fun setOnItemClicklistener(listener: onItemClickListener){
        mListener =listener
    }
    inner class ViewHolder(listener: onItemClickListener,v: View):RecyclerView.ViewHolder(v){
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
        val product = list[position]
        Glide.with(con).load(list[position].image ).into(holder.img)
=======
        val product = list[position] // Get the ProductsItem at the current position

        Glide.with(con).load(product.image.imageURL).into(holder.img)
>>>>>>> a148e7b785ed35a19780ccfd9ed4b6937950f589

        holder.name.text =list[position].name
        holder.price.text =list[position].price.toString()

        // Set the addIcon image
        holder.add.setImageResource(R.drawable.add_icon)
<<<<<<< HEAD

        holder.itemView.setOnClickListener {         // Create an Intent to start the new activity
            val intent = Intent(holder.itemView.context, ViewOneActivity2::class.java)
        // Pass the data for the clicked item through the Intent
            //intent.putExtra("productId", product.id)
            intent.putExtra("productName", product.price)
            intent.putExtra("productCategory", product.category)
            intent.putExtra("productDescription", product.description)
            intent.putExtra("productTitle", product.title)
            intent.putExtra("productImage", product.image)
            val productImage  = list[position].image // Assuming "image" is the URL obtained from the API
            intent.putExtra("productImage", productImage)

            // Add more data as needed
        // Start the new activity
          holder.itemView.context.startActivity(intent)     }
    }
=======
        holder.itemView.setOnClickListener {         // Create an Intent to start the new activity
            val intent = Intent(holder.itemView.context, ViewOneActivity2::class.java)
            // Pass the data for the clicked item through the Intent
            //intent.putExtra("productId", product.id)
            //intent.putExtra("productName", product.price)
            val cat  = list[position].category.name
            intent.putExtra("productCategory", cat)
            intent.putExtra("productDescription", product.description)
            intent.putExtra("productTitle", product.name)
            intent.putExtra("productPrice",product.price)
            val productImage  = list[position].image.imageURL // Assuming "image" is the URL obtained from the API
            intent.putExtra("productImage", productImage)

            // Add more data as needed
            // Start the new activity
            holder.itemView.context.startActivity(intent)     }
>>>>>>> a148e7b785ed35a19780ccfd9ed4b6937950f589

    }

}