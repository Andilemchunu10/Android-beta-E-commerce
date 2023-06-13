package com.example.android_beta_e_commerce

data class ApiResponse(
    val data: List<ProductsItem>,
    val message: String,
    val status: Int
)

