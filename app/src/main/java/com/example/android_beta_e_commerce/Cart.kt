package com.example.android_beta_e_commerce

import android.content.Intent
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
    private var list = ArrayList<ProductsItem>()
    private lateinit var orderTotalTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val backbuttons: ImageView = findViewById(R.id.imageView)

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


        private fun placeOrder() {
        Toast.makeText(this, "Order placed Successfully !!", Toast.LENGTH_SHORT).show()
        // Perform the order placement logic here
    }




}
