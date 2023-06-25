package com.example.android_beta_e_commerce

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cartCount = MutableLiveData<Int>()
    private val _cartCountVisibility = MutableLiveData<Int>()

    val cartCount: LiveData<Int> get() = _cartCount
    val cartCountVisibility: LiveData<Int> get() = _cartCountVisibility

    fun updateCartCount(count: Int) {
        _cartCount.value = count

        // Set the visibility based on the count
        if (count > 0) {
            _cartCountVisibility.value = View.VISIBLE
        } else {
            _cartCountVisibility.value = View.INVISIBLE
        }
    }
}