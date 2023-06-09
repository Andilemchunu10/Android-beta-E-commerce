package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var homeIcon:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        backButton =  findViewById(R.id.imageViewBack)
        homeIcon= findViewById(R.id.homeIcon)

        backButton.setOnClickListener {
            val intent = Intent( this, Home::class.java)
            startActivity(intent)
        }

        homeIcon.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }


}