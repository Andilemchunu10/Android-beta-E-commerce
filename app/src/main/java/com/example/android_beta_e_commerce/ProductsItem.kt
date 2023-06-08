package com.example.android_beta_e_commerce

<<<<<<< HEAD
data class  ProductsItem(
    val category: String,
=======
data class ProductsItem(
    val category: Category,
>>>>>>> 5ce3390 (added the viewall with the backend api)
    val description: String,
    val image: Image,
    val imageURL: String,
    val name: String,
    val price: Double,
    val productId: Int
)