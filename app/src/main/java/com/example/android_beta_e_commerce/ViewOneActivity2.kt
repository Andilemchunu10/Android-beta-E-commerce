package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class ViewOneActivity2 : AppCompatActivity() {

    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productDescriptionView: TextView
    private lateinit var productImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_one2)
        val backbutton: ImageView = findViewById(R.id.imageView2)
        val favourites: ImageView = findViewById(R.id.imageView4)
        val addToCartbutton: Button = findViewById(R.id.button)
        val incrementBtn: ImageButton = findViewById(R.id.imageButton3)
        val decrementBtn: ImageButton = findViewById(R.id.imageButton2)
        val itemNum: TextView = findViewById(R.id.textView8)
        val homeImage: ImageView = findViewById(R.id.homeIcon)

        productNameTextView = findViewById(R.id.textView)
        productPriceTextView = findViewById(R.id.textView2)
//        productDescriptionView = findViewById(R.id.descriptiontxt)
        productImageView = findViewById(R.id.imageView3)

        val productId = intent.getStringExtra("productId")

//        if (productId != null && productId.isNotEmpty()) {
//            val product = getProductDetails(productId) // Replace with your own method to fetch product details
//            if (product != null) {
//                displayProductDetails(product)
//            } else {
//                // Handle the case when the product is not found
//            }
//        } else {
//            // Handle the case when the product ID is null or empty
//        }
    }



    private fun displayProductDetails(product: ProductsItem) {
        productNameTextView.text = product.title
        productPriceTextView.text = product.price.toString()
        productDescriptionView.text = product.description
        // productImageView.setImageResource(product.img)
        // Update other UI elements as needed
    }

    override fun onResume() {
        super.onResume()

        val backbutton: ImageView = findViewById(R.id.imageView2)
        val favourites: ImageView = findViewById(R.id.imageView4)
        val addToCartbutton: Button = findViewById(R.id.button)
        val incrementBtn: ImageButton = findViewById(R.id.imageButton3)
        val decrementBtn: ImageButton = findViewById(R.id.imageButton2)
        val itemNum: TextView = findViewById(R.id.textView8)
        val homeImage: ImageView = findViewById(R.id.homeIcon)
        var pricetxt: TextView = findViewById(R.id.textView2)


        backbutton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        homeImage.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        var count = 0



        incrementBtn.setOnClickListener {
            var pricetxt = 0.0
            count++
            itemNum.text = count.toString()
            pricetxt *= count
            pricetxt.toString()
        }

        decrementBtn.setOnClickListener {
            if (count > 0) {
                count--
                itemNum.text = count.toString()
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
            Snackbar.make(it, "Added to Cart", Snackbar.LENGTH_SHORT).show()
        }
    }
}
