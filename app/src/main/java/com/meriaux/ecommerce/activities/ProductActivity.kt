package com.meriaux.ecommerce.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import com.meriaux.ecommerce.Database
import com.meriaux.ecommerce.beans.Product
import com.meriaux.ecommerce.databinding.ProductLayoutBinding
import com.meriaux.ecommerce.objects.CartEntity
import com.meriaux.ecommerce.viewmodels.ProductViewModel
import com.meriaux.ecommerce.viewmodels.ProductViewModelFactory

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ProductLayoutBinding
    private lateinit var database: Database
    private lateinit var productViewModel: ProductViewModel

    private fun initDatabase() {
        database = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "database"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()

        val product = intent.extras?.getSerializable("product") as Product
        binding.productPageName.text = product.title
        binding.productPagePrice.text = product.price.toString() + " €"
        binding.productPageDescription.text = product.description
        Glide.with(this).load(product.image).into(binding.productPageImage)
        binding.productPageRating.text = "rating : " + product.rating.rate
        binding.productPageCount.text = "from " + product.rating.count + " reviews"
        // add a  view model provider for the product view model
        productViewModel = ViewModelProvider(this, ProductViewModelFactory(database)).get(ProductViewModel::class.java)

        // button addToCart setOnClickListener
        binding.addToCart.setOnClickListener {
            var cartEntity = CartEntity(1, product.id, 1)
            productViewModel.insertData(cartEntity = cartEntity)
            Log.i("test", "added to cart")
            // log.i cart content
            Log.i("test", productViewModel.fetchData().toString())
        }
    }
}