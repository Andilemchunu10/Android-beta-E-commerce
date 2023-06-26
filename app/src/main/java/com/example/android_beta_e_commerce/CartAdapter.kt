package com.example.android_beta_e_commerce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class CartAdapter(
    private val con: Context,
    private val orderTotalTextView: TextView,
    private val recyclerView: RecyclerView,
    private val updateOrderTotal: () -> Unit,
    private val updateCartCount: (Int) -> Unit,
    var list: List<ProductsItem>
    ) :RecyclerView.Adapter<CartAdapter.ViewHolder>(){


    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var img = v.findViewById<ImageView>(R.id.cartImg)
        var name = v.findViewById<TextView>(R.id.cartProductName)
        var price = v.findViewById<TextView>(R.id.cartPrice)
        var increment = v.findViewById<ImageButton>(R.id.increment)
        var decrement = v.findViewById<ImageButton>(R.id.decrement)
        var counts =  v.findViewById<TextView>(R.id.counts)
        val rootView = v

        var count = 1

        init {

            // Set click listeners for increment and decrement buttons
            increment.setOnClickListener {
                // Handle increment button click
                count++
                counts.text = count.toString()

                counts.text = count.toString()
                val updatedPrice = list[adapterPosition].price * count
                val priceFormatted = String.format("%.2f", updatedPrice)
                price.text = priceFormatted

            }

            decrement.setOnClickListener {
                // Handle decrement button click
                if (count > 1) {
                    count--
                    counts.text = count.toString()
                    val initialPrice = list[adapterPosition].price
                    val updatedPrice = initialPrice * count
                    val priceFormatted = String.format("%.2f", updatedPrice)
                    price.text = priceFormatted
                }


                // Add your desired logic here
            }
        }
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

        val incrementButton = holder.increment
        val decrementButton = holder.decrement
        val counts = holder.counts
        val price = holder.price

        holder.rootView.setOnLongClickListener {
            removeItem(holder.adapterPosition)
            true
        }
    }

    fun removeItem(position: Int) {
        val deletedItem = list[position]
        CartManager.removeItem(deletedItem)
        notifyItemRemoved(position)
        updateOrderTotal()
        updateCartCount(CartManager.getCartItems().size)

        Snackbar.make(recyclerView, "Item removed", Snackbar.LENGTH_SHORT)
            .setAction("Undo") {
                CartManager.addItem(deletedItem, 1)
                notifyItemInserted(position)
                updateOrderTotal()
                updateCartCount(CartManager.getCartItems().size)
            }
            .show()
    }


}
