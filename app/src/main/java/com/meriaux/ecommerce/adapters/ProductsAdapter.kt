package com.meriaux.ecommerce.adapters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meriaux.ecommerce.beans.Product
import com.meriaux.ecommerce.R
import com.meriaux.ecommerce.activities.ProductActivity

class ProductsAdapter(private var productList : ArrayList<Product>, private var listener : OnItemClickListener) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){
    class ViewHolder(itemView: View, var listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.productLayout)
        fun bind(item: Product) {
            title.text = item.title
            price.text = "price : " + item.price.toString()
            Glide.with(itemView.context).load(item.image).into(image)
            constraintLayout.setOnClickListener {
                Log.i("test", "clicked on " + item.title)
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}