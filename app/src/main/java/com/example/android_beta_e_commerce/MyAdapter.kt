package com.example.android_beta_e_commerce

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(
    private val con: Context,
    var list: List<ProductsItem>,
    private val cartCount: TextView
    ) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener
    private val cartItems: MutableList<ProductsItem> = mutableListOf()
    private var count = 1

    interface onItemClickListener{
        fun onItemClickListener(position: Int)
        fun onBindViewHolder(holder: ViewHolder, position: Int)
    }

    private var filteredList: List<ProductsItem> = list

    fun filterByCategory(category: String) {
        filteredList = list.filter { item -> item.category.name  == category}
        notifyDataSetChanged()
    }

    fun setOnItemClicklistener(listener: onItemClickListener){
        mListener =listener
    }
    inner class ViewHolder(listener: onItemClickListener,v: View):RecyclerView.ViewHolder(v){
        var img = v.findViewById<ImageView>(R.id.productImg)
        var name = v.findViewById<TextView>(R.id.productName)
        var price = v.findViewById<TextView>(R.id.productPrice)
        var add = v.findViewById<ImageView>(R.id.addIcon)
        var cartCount = v.findViewById<TextView>(R.id.cartCount)
        var count = 0

        init {
            v.setOnClickListener {
                listener.onItemClickListener(adapterPosition)
            }

            add.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = filteredList[position]
                    addToCart(product)

                }
            }

        }
    }
    private fun addToCart(product: ProductsItem) {
        cartItems.add(product)
        count++
        notifyDataSetChanged()
        // Get the cart items from the CartManager
        val cartItems = CartManager.getCartItems()

        // Set cartCount visibility based on the cartItems size
        cartCount.visibility = if (cartItems.isNotEmpty()) {
            updateCartCount(cartItems.size)
            View.VISIBLE // Display the cartCount TextView
        } else {
            View.INVISIBLE // Hide the cartCount TextView
        }

        // Update UI or perform any other actions related to adding the product to the cart
    }
    private fun updateCartCount(count: Int) {
        cartCount.text = count.toString()
    }

    fun setFilteredList(list: List<ProductsItem>){
        this.filteredList = list

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(con).inflate(R.layout.item,parent,false)
        return ViewHolder(mListener,view)

    }

    override fun getItemCount(): Int {
        return filteredList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = filteredList[position] // Get the ProductsItem at the current position


        Glide.with(con).load(product.image.imageURL).into(holder.img)

        holder.name.text =filteredList[position].name
        holder.price.text =filteredList[position].price.toString()

        // Set the addIcon image
        holder.add.setImageResource(R.drawable.add_icon)
        holder.add.setOnClickListener {
            val product = filteredList[position]

            if (CartManager.isProductAdded(product)) {
            Toast.makeText(con, "Product is already added to the cart", Toast.LENGTH_SHORT).show()
        }else{

                val quantity = count
                CartManager.addItem(product)
                addToCart(product)
                Toast.makeText(con, "Item added to cart", Toast.LENGTH_SHORT).show() // Update UI or perform any other actions related to adding the product to the cart
        }


        }

        holder.itemView.setOnClickListener {         // Create an Intent to start the new activity
            val intent = Intent(holder.itemView.context, ViewOneActivity2::class.java)
            // Pass the data for the clicked item through the Intent
            //intent.putExtra("productId", product.id)
            //intent.putExtra("productName", product.price)
            val cat  = filteredList[position].category.name
            intent.putExtra("productCategory", cat)
            intent.putExtra("productDescription", product.description)
            intent.putExtra("productTitle", product.name)
            intent.putExtra("productPrice",product.price)
            val productImage  = filteredList[position].image.imageURL // Assuming "image" is the URL obtained from the API
            intent.putExtra("productImage", productImage)

            // Add more data as needed
            // Start the new activity
            holder.itemView.context.startActivity(intent)     }

    }



}