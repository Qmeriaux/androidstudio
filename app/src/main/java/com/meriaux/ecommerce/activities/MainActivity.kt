package com.meriaux.ecommerce.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.meriaux.ecommerce.adapters.ProductsAdapter
import com.meriaux.ecommerce.beans.Product
import com.meriaux.ecommerce.databinding.ActivityMainBinding
import com.meriaux.ecommerce.objects.RetrofitApi
import com.meriaux.ecommerce.viewmodels.RetrofitViewModel
import kotlin.math.log

class MainActivity : AppCompatActivity(), ProductsAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var categoriesAdapter: ArrayAdapter<String>
    private var productList = ArrayList<Product>()
    private var categoriesList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val retrofitViewModel = RetrofitViewModel(RetrofitApi)
        val categories = retrofitViewModel.getCategories()
        retrofitViewModel.data.observe(this) {
            // for each product in the list, add it to the productList
            //clear list before
            productList.clear()
            for (product in it) {
                productList.add(product)
                //notify adapter
                productsAdapter.notifyDataSetChanged()
            }
        }


        retrofitViewModel.categories.observe(this) {
            categoriesList.clear()
            categoriesList.add("All")
            for (category in it) {
                categoriesList.add(category)
            }

            //notify adapter
            categoriesAdapter.notifyDataSetChanged()

            binding.categoriesList.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = categoriesList[position]
                    Log.i("test", selectedCategory)
                    if (selectedCategory == "All") {
                        retrofitViewModel.getAll()
                    } else {
                        retrofitViewModel.getByCategory(selectedCategory)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("MainActivity", "Nothing selected")
                }
            }

        }


        // Products recyclerView
        val recyclerViewVariable = binding.productsRecyclerView
        productsAdapter = ProductsAdapter(productList, listener = this)

        // Categories Spinner
        val categoriesSpinner = binding.categoriesList
        categoriesAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesList)

        with(recyclerViewVariable) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = productsAdapter
        }

        with(categoriesSpinner) {
            adapter = categoriesAdapter
        }

        // get categories, selected category and products
        retrofitViewModel.getCategories()
        retrofitViewModel.getAll()
    }

    override fun onItemClick(position: Int) {
        Log.i("test", "clicked on " + productList[position].title)
        val intent = Intent(applicationContext, ProductActivity::class.java)
        intent.putExtra("product", productList[position])
        startActivity(intent)
    }
}
