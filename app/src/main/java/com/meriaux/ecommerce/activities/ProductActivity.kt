package com.meriaux.ecommerce.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meriaux.ecommerce.beans.Product
import com.meriaux.ecommerce.databinding.ProductLayoutBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ProductLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.extras?.getSerializable("product") as Product
        binding.productName.text = product.title

    }
}