package com.example.android_beta_e_commerce
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

import android.widget.Toast
import com.bumptech.glide.Glide

import com.google.android.material.snackbar.Snackbar

class ViewOneActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_one2)
        val backbutton: ImageView = findViewById(R.id.imageView2)
        val favourites: ImageView = findViewById(R.id.imageView4)
        val addToCartbutton: Button = findViewById(R.id.button)
        val incrementBtn: ImageButton = findViewById(R.id.imageButton3)
        val decrementBtn: ImageButton = findViewById(R.id.imageButton2)
        val itemNum: TextView = findViewById(R.id.textView8)
        //val homeImage: ImageView = findViewById(R.id.homeIcon)


        val image: ImageView = findViewById(R.id.imageView3)
        val price: TextView = findViewById(R.id.textView2)
        val category: TextView = findViewById(R.id.textView)
        val description: TextView = findViewById(R.id.textView5)
        val title: TextView = findViewById(R.id.title)

        //val productDescription = intent.getStringExtra("productDescription")

        val bundle: Bundle? = intent.extras
        val productDescription = bundle!!.getString("productDescription")
        val productCategory = bundle.getString("productCategory")
        val productTitle = bundle.getString("productTitle")

        val productPrice = bundle.getDouble("productPrice")
        val productImage = bundle.getString("productImage") // Retrieve the image URL
        val priceFormatted = String.format("%.2f", productPrice)
        Glide.with(this).load(productImage).into(image)

        description.text = productDescription
        category.text = productCategory
        price.text = priceFormatted
        title.text = productTitle
        //image.= productPrice


        backbutton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

//        homeImage.setOnClickListener {
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
//        }

        var count = 1

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
//            val intent = Intent(this, AddToCart::class.java)
//            startActivity(intent)
        }

    }
}
