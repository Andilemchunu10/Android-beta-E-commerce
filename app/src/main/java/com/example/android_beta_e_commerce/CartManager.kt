package com.example.android_beta_e_commerce

object CartManager {
    private val cartItems: MutableList<ProductsItem> = mutableListOf()

    fun addItem(item: ProductsItem) {
        cartItems.add(item)
    }

    fun removeItem(item: ProductsItem) {
        cartItems.remove(item)
    }

    fun getCartItems(): List<ProductsItem> {
        return cartItems
    }
}
