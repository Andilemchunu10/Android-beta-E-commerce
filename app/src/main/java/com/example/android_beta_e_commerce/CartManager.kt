package com.example.android_beta_e_commerce

object CartManager {
    private val cartItems: MutableList<ProductsItem> = mutableListOf()

    private var cartCount: Int = 0



    fun getCartCount(): Int {
        return cartCount
    }

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


    fun clearCart() {
        cartItems.clear()
    }

    fun isProductAdded(item: ProductsItem): Boolean {
        return cartItems.contains(item)
    }

    fun getItemQuantity(item: ProductsItem): Int {
        var quantity = 0
        for (cartItem in cartItems) {
            if (cartItem == item) {
                quantity++
            }
        }
        return quantity
    }

}
