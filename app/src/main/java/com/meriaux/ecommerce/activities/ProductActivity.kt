package com.meriaux.ecommerce.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.meriaux.ecommerce.beans.Product
import com.meriaux.ecommerce.databinding.ProductLayoutBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ProductLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.extras?.getSerializable("product") as Product
        binding.productPageName.text = product.title
        binding.productPagePrice.text = product.price.toString() + " €"
        binding.productPageDescription.text = product.description
        Glide.with(this).load(product.image).into(binding.productPageImage)
        binding.productPageRating.text = "rating : " + product.rating.rate
        binding.productPageCount.text = "from " + product.rating.count + " reviews"
    }
}