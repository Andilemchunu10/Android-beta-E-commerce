package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        val homeImage: ImageView = findViewById(R.id.homeIcon)

        backbutton.setOnClickListener {
            val intent = Intent (this, Home::class.java)
            startActivity(intent)
        }

        homeImage.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        incrementBtn.setOnClickListener {
            var count = 0
            count++
            itemNum.text = count.toString()


        }


            decrementBtn.setOnClickListener {
                var count = 0
                if (count > 0) {
                    count--
                    itemNum.text = count.toString()

                }
        }
        var isFavorite = false
        favourites.setOnClickListener {

            // Perform your logic to add/remove from favorites here

            isFavorite = !isFavorite
             if (isFavorite) {

//
                 favourites.setImageResource(R.drawable.fav)
                Snackbar.make(it, "Added to favorites", Snackbar.LENGTH_SHORT).show()

            } else {

                 favourites.setImageResource(R.drawable.hart_icon_white)
                Snackbar.make(it, "Removed from favorites", Snackbar.LENGTH_SHORT).show()
//
            }


        }
        addToCartbutton.setOnClickListener {
            Snackbar.make(it, "Added to Cart", Snackbar.LENGTH_SHORT).show()
        }

    }
}