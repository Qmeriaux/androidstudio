package com.meriaux.ecommerce.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.meriaux.ecommerce.objects.CartEntity

@Dao
interface CartDao {
    @Insert
    suspend fun insert(cart: CartEntity)

    @Update
    suspend fun update(cart: CartEntity)

    @Delete
    suspend fun delete(cart: CartEntity)

    @Query("SELECT * FROM cartentity")
    suspend fun getAll(): List<CartEntity>
}