package com.meriaux.ecommerce.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meriaux.ecommerce.Database
import com.meriaux.ecommerce.objects.CartEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(
    private val database: Database
) : ViewModel() {
    private var _item = MutableLiveData<List<CartEntity>>()
    val item : LiveData<List<CartEntity>> = _item

    init {
        fetchData()
    }

    fun insertData(cartEntity: CartEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.getCartDao().insert(cartEntity)
            fetchData()
        }
    }

    fun deleteData(cartEntity: CartEntity) {
        viewModelScope.launch {
            database.getCartDao().delete(cartEntity)
            fetchData()
        }
    }

    fun updateData(cartEntity: CartEntity) {
        viewModelScope.launch {
            database.getCartDao().update(cartEntity)
            fetchData()
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            _item.value = database.getCartDao().getAll()
        }
    }
}