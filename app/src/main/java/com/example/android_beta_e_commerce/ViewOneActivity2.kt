package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class ViewOneActivity2 : AppCompatActivity() {


    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productDescriptionView: TextView
    private lateinit var productImageView: ImageView
    private lateinit var cartIcon: ImageView
    private lateinit var cartCount: TextView
    private lateinit var profileIcon: ImageView
    private lateinit var homeIcon: ImageView
    private var count = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_one2)
        val backbutton: ImageView = findViewById(R.id.imageView2)
        val favourites: ImageView = findViewById(R.id.imageView4)
        val addToCartbutton: Button = findViewById(R.id.button)
        val incrementBtn: ImageButton = findViewById(R.id.imageButton3)
        val decrementBtn: ImageButton = findViewById(R.id.imageButton2)
        val itemNum: TextView = findViewById(R.id.textView8)

        cartIcon = findViewById(R.id.cartIcon)
        cartCount = findViewById(R.id.cartCount)
        homeIcon = findViewById(R.id.homeIcon)

        val image: ImageView = findViewById(R.id.imageView3)
        val price: TextView = findViewById(R.id.textView2)
        val category: TextView = findViewById(R.id.textView)
        val description: TextView = findViewById(R.id.textView5)
        val title: TextView = findViewById(R.id.title)

        //val productDescription = intent.getStringExtra("productDescription")


        val bundle: Bundle? = intent.extras
        val productDescription = bundle?.getString("productDescription")
        val productCategory = bundle?.getString("productCategory")
        val productTitle = bundle?.getString("productTitle")

        val productPrice = bundle?.getDouble("productPrice")
        val productImage = bundle?.getString("productImage")
        val priceFormatted = productPrice?.let { String.format("%.2f", it) }
        Glide.with(this).load(productImage).into(image)

        description.text = productDescription
        category.text = productCategory
        price.text = priceFormatted
        title.text = productTitle

        val cartItems = CartManager.getCartItems()
        updateCartCount(cartItems.size)

        cartCount.visibility = if (cartItems.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

        backbutton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        homeIcon.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        cartIcon.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }


//        homeImage.setOnClickListener {
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
//        }

        var count = 1


        incrementBtn.setOnClickListener {
            count++
            itemNum.text = count.toString()
            updatePrice(bundle, price)
        }

        decrementBtn.setOnClickListener {

            if (count > 1) {

                count--
                itemNum.text = count.toString()
                updatePrice(bundle, price)
            }
        }

        var isFavorite = false
        favourites.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                favourites.setImageResource(R.drawable.fav)
                Snackbar.make(it, "Added to favorites", Snackbar.LENGTH_SHORT).show()
            } else {
                favourites.setImageResource(R.drawable.hart_icon_white)
                Snackbar.make(it, "Removed from favorites", Snackbar.LENGTH_SHORT).show()
            }
        }


        addToCartbutton.setOnClickListener {

            val product = ProductsItem(
                name = productTitle ?: "",
                description = "",
                category = Category(0, ""),
                price = productPrice ?: 0.0,
                image = Image(0, productImage ?: ""),
                productId = 0
            )
            if (CartManager.isProductAdded(product)) {
                Toast.makeText(this, "Product is already added to the cart", Toast.LENGTH_SHORT).show()
            } else {
                val quantity = count
                CartManager.addItem(product, quantity)
                Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()
                updateCartCount(CartManager.getCartItems().size)
            }
        }


        cartIcon.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)

        }
    }

    private fun updatePrice(bundle: Bundle?, price: TextView) {
        val initialPrice = bundle?.getDouble("productPrice") ?: 0.0
        val updatedPrice = initialPrice * count
        val priceFormatted = String.format("%.2f", updatedPrice)
        price.text = priceFormatted
    }

    private fun updateCartCount(count: Int) {
        cartCount.text = count.toString()
    }
}
