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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var placeOrderButton: Button
    private lateinit var orderTotalTextView: TextView
    private lateinit var cartCount: TextView
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val backbutton: ImageView = findViewById(R.id.imageView)

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


        // Set click listener for the back button
        backbutton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun updateCartCount(count: Int) {
        cartCount.text = count.toString()
    }


    private fun placeOrder() {
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