package com.meriaux.ecommerce.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meriaux.ecommerce.Database

class ProductViewModelFactory(val database: Database) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(database) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}