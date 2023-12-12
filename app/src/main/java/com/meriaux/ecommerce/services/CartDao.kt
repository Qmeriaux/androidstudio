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
    fun insert(cart: CartEntity)

    @Update
    fun update(cart: CartEntity)

    @Delete
    fun delete(cart: CartEntity)

    @Query("SELECT * FROM cartentity")
    fun getAll(): List<CartEntity>
}