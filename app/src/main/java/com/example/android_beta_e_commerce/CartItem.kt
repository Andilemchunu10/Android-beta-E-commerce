package com.example.android_beta_e_commerce

import android.widget.ImageButton
import android.widget.TextView

data class CartItem(
    val product: ProductsItem,
    val quantity: Int

) {
    override fun equals(other: Any?): Boolean {


        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartItem

        if (product != other.product) return false
        if (quantity != other.quantity) return false

        return true


    }
    override fun toString(): String {
        return "CartItem(product=$product, quantity=$quantity)"
    }
}
