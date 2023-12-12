package com.meriaux.ecommerce.services

import com.meriaux.ecommerce.beans.Product
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {
    @GET("/products")
    suspend fun getAll(): List<Product>

    @GET("/products/categories")
    suspend fun getCategories(): List<String>

    @GET("/products/category/{category}")
    suspend fun getByCategory(@Path("category") category: String): List<Product>
}