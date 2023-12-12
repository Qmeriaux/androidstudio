package com.meriaux.ecommerce.beans

import java.io.Serializable

data class Rating (
        var rate: Double = 0.0,
        var count: Int = 0
) : Serializable
