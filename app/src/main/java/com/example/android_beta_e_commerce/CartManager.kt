package com.example.android_beta_e_commerce

object CartManager {
    private val cartItems: MutableList<ProductsItem> = mutableListOf()

    fun addItem(item: ProductsItem) {
        if (!cartItems.contains(item)) {
            cartItems.add(item)
        }
    }

    fun removeItem(item: ProductsItem) {
        cartItems.remove(item)
    }

    fun getCartItems(): List<ProductsItem> {
        return cartItems
    }

    fun getCartCount(): Int {
        return cartItems.size
    }

    fun isProductAdded(item: ProductsItem): Boolean {
        return cartItems.contains(item)
    }
}
