package com.meriaux.ecommerce

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meriaux.ecommerce.objects.CartEntity
import com.meriaux.ecommerce.services.CartDao


@Database(
    entities = [CartEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getCartDao(): CartDao
}