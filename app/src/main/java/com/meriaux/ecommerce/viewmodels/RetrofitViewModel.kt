package com.meriaux.ecommerce.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meriaux.ecommerce.beans.Product
import com.meriaux.ecommerce.objects.RetrofitApi
import com.meriaux.ecommerce.services.RetrofitService
import kotlinx.coroutines.launch

class RetrofitViewModel (
    private val retrofitApi: RetrofitApi
): ViewModel() {
    private val _data = MutableLiveData<List<Product>>()
    private val _categories = MutableLiveData<List<String>>()
    val data: LiveData<List<Product>> = _data
    val categories: LiveData<List<String>> = _categories
    private var service: RetrofitService = retrofitApi.getService()


    fun getAll() {
        viewModelScope.launch {
            _data.value = service.getAll()
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = service.getCategories()
        }
    }

    fun getByCategory(category: String) {
        Log.i("test", category)
        viewModelScope.launch {
            _data.value = service.getByCategory(category)
        }
    }
}