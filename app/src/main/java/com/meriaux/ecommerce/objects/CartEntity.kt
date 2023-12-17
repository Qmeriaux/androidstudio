package com.meriaux.ecommerce.objects

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "product_id")
    var product_id: Int,

    @ColumnInfo(name = "quantity")
    var quantity: Int
) : Parcelable
