package com.example.android_beta_e_commerce

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class Cart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var placeOrderButton: Button
    private lateinit var orderTotalTextView: TextView
    private lateinit var cartCount: TextView
    private var list = ArrayList<ProductsItem>()
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        val backbuttons: ImageView = findViewById(R.id.imageView)


        // Initialize views and adapters
        recyclerView = findViewById(R.id.RecyclerViewList)
        placeOrderButton = findViewById(R.id.placeOrderButton)
        orderTotalTextView = findViewById(R.id.orderTotalTextView)
        cartCount = findViewById(R.id.cartCount)
        sharedPreferences = getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)

        // Get the cart items from the CartManager
        val cartItems = CartManager.getCartItems()


        // Update the cart count TextView
        updateCartCount(CartManager.getCartItems().size)

        // Set cartCount visibility based on the cartItems size
        cartCount.visibility = if (CartManager.getCartItems().isNotEmpty()) {
            View.VISIBLE // Display the cartCount TextView
        } else {
            View.INVISIBLE // Hide the cartCount TextView
        }

        // Set the CartAdapter and RecyclerView
        cartAdapter = CartAdapter(this, cartItems)
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set click listener for the place order button
        placeOrderButton.setOnClickListener {
            placeOrder()
        }
        ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
              val deletedProducts:ProductsItem = list.get(viewHolder.adapterPosition)

                val position = viewHolder.adapterPosition
                list.removeAt(viewHolder.adapterPosition)
                cartAdapter.notifyItemRemoved(viewHolder.adapterPosition)

             Snackbar.make(recyclerView,"Successfully Deleted" + deletedProducts.name,Snackbar.LENGTH_SHORT).setAction(
                 "Undo", View.OnClickListener {
                     list.add(position,deletedProducts)
                     cartAdapter.notifyItemInserted(position)
                 }).show()
            }

        }).attachToRecyclerView(recyclerView)

        backbuttons.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }

    private fun updateCartCount(count: Int) {
        cartCount.text = count.toString()
    }



        private fun placeOrder() {
        Toast.makeText(this, "Order placed Successfully !!", Toast.LENGTH_SHORT).show()
        // Perform the order placement logic here
        // Clear the cart or update the count based on your implementation
        CartManager.clearCart()

        // Update the cart count TextView
        updateCartCount(0)
    }


    override fun onResume() {
        super.onResume()

        // Get the latest cart items from the CartManager
        val cartItems = CartManager.getCartItems()

        // Update the cart count TextView
        updateCartCount(cartItems.size)

        // Set cartCount visibility based on the cartItems size
        cartCount.visibility = if (cartItems.isNotEmpty()) {
            View.VISIBLE // Display the cartCount TextView
        } else {
            View.INVISIBLE // Hide the cartCount TextView
        }
    }
}






