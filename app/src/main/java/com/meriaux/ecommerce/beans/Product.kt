package com.meriaux.ecommerce.beans

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("price")
    var price: Double,

    @SerializedName("description")
    var description: String,

    @SerializedName("category")
    var category: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("rating")
    var rating: Rating = Rating()
) : Serializable
