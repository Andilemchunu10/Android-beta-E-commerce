package com.example.android_beta_e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_beta_e_commerce.adapters.ProductAdapters
import com.example.android_beta_e_commerce.model.Model


//

class Home : AppCompatActivity(){

    private lateinit var productsAdapter: ProductAdapters
    private lateinit var modelList: List<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

}