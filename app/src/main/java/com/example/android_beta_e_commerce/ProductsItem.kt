package com.example.android_beta_e_commerce



data class ProductsItem(
    val category: Category,
    val description: String,
    val image: Image,
    val name: String,
    val price: Double,
    val productId: Int
)

