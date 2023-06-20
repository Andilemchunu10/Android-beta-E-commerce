package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
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
    private var list = ArrayList<ProductsItem>()
    private lateinit var orderTotalTextView: TextView
    private lateinit var incrementBtn : ImageButton
    private lateinit var decrementBtn: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val backbutton: ImageView = findViewById(R.id.imageView)

        // Initialize views and adapters
        recyclerView = findViewById(R.id.RecyclerViewList)
        placeOrderButton = findViewById(R.id.placeOrderButton)
        orderTotalTextView = findViewById(R.id.orderTotalTextView)
        // Get the cart items from the CartManager
        val cartItems = CartManager.getCartItems()

        cartAdapter = CartAdapter(this,cartItems)
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set click listener for the place order button
        placeOrderButton.setOnClickListener {
            placeOrder()
        }

        backbutton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }


    }




    private fun placeOrder() {
        // Perform the order placement logic here
    }

}