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
                val position = adapterPosition
                val item = list[adapterPosition]
                val quantity = CartManager.getItemQuantity(item)
                val newQuantity = quantity + 1
                updateCartItemQuantity(position, newQuantity)

                count++
                counts.text = count.toString()
                list[adapterPosition].quantity = count
                val updatedPrice = list[adapterPosition].price * count
                val priceFormatted = String.format("%.2f", updatedPrice)

                CartManager.setItemQuantity(item, count)


                price.text = priceFormatted


                // Update the cart count TextView
                updateCartCount(CartManager.getCartCount())

                (con as Cart).updateOrderTotal()

            }

            decrement.setOnClickListener {
                // Handle decrement button click
                if (count > 1) {
                    count--
                    counts.text = count.toString()
                    list[adapterPosition].quantity = count
                    val initialPrice = list[adapterPosition].price
                    val updatedPrice = initialPrice * count
                    val priceFormatted = String.format("%.2f", updatedPrice)
                    price.text = priceFormatted

                    val item = list[adapterPosition]
                    CartManager.setItemQuantity(item, count)

                    // Update the cart count TextView
                    //updateCartCount(CartManager.getCartCount())
                    updateOrderTotal()
                }


                // Add your desired logic here
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.cart_row,parent,false)
        return ViewHolder(view)
    }

    fun updateCartItemQuantity(position: Int, quantity: Int) {
        val item = list[position]
        CartManager.setItemQuantity(item, quantity)
        notifyItemChanged(position)
        updateOrderTotal()
        updateCartCount(CartManager.getCartCount())
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val incrementButton = holder.increment
        val decrementButton = holder.decrement
        val counts = holder.counts
        val price = holder.price
        val product = list[position]

        holder.count = CartManager.getItemQuantity(product)
        counts.text = holder.count.toString()
        val initialPrice = list[position].price
        val totalPrice = initialPrice * holder.count
        val priceFormatted = String.format("%.2f", totalPrice)
        price.text = priceFormatted

        // Get the ProductsItem at the current position
        Glide.with(con).load(product.image.imageURL).into(holder.img)
        holder.name.text = list[position].name
        holder.price.text = list[position].price.toString()
        val item = list[position]
        val quantity = CartManager.getItemQuantity(item)

        holder.name.text = item.name
        holder.price.text = item.price.toString()
        holder.counts.text = quantity.toString()



        holder.rootView.setOnLongClickListener {
            removeItem(holder.adapterPosition)
            true
        }
    }

    fun addItem(position: Int) {
        val product = list[position]
        CartManager.addItem(product, 1)
        notifyItemChanged(position)
        updateOrderTotal()
        updateCartCount(CartManager.getCartCount())
    }


    fun removeItem(position: Int) {
        val deletedItem = list[position]
        CartManager.removeItem(deletedItem)
        notifyItemRemoved(position)
        updateOrderTotal()
        updateCartCount(CartManager.getCartItems().size)

        Snackbar.make(recyclerView, "Item removed", Snackbar.LENGTH_SHORT)
            .setAction("") {
                CartManager.addItem(deletedItem, 1)
                notifyItemInserted(position)
                updateOrderTotal()
                updateCartCount(CartManager.getCartCount())
            }
            .show()
    }


}
