package com.example.android_beta_e_commerce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val cartItems: MutableList<ProductsItem> = mutableListOf()
    private val _cartCount = MutableLiveData<Int>()
    val cartCount: LiveData<Int>
        get() = _cartCount

    fun updateCartCount(count: Int) {
        _cartCount.value = count
    }

    fun addItem(item: ProductsItem, quantity: Int) {
        if (!cartItems.contains(item)) {
            cartItems.add(item)
        }
    }

    fun getCartItems(): List<ProductsItem> {
        return cartItems
    }

}