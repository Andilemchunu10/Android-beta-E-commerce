package com.example.android_beta_e_commerce

object CartManager {
    private val cartItems: MutableList<ProductsItem> = mutableListOf()
    private val itemQuantities: MutableMap<ProductsItem, Int> = mutableMapOf()

    private var cartCount: Int = 0



    fun getCartCount(): Int {
        return cartCount
    }

    fun addItem(item: ProductsItem, quantity: Int) {
        if (!cartItems.contains(item)) {
            cartItems.add(item)
        }
        cartCount += quantity

        // Set the initial quantity of the item to 1 if it's newly added
        if (quantity == 0) {
            item.quantity = 1
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
        // Implement the logic to get the quantity of the item in the cart
        // For now, assuming the quantity is always 1
        return itemQuantities[item] ?: 0
    }

    fun setItemQuantity(item: ProductsItem, quantity: Int) {
        itemQuantities[item] = quantity
    }
}
