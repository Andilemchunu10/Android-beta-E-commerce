package com.example.android_beta_e_commerce

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.content.Intent

import android.widget.Button

class LandingPage : AppCompatActivity() {
    lateinit var btn_order: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        this.findViewById<Button>(R.id.btn_order).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }
}