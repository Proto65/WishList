package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishListAdapter(private val items: MutableList<WishList>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemNameText.text = item.item
        holder.itemPriceText.text = item.price
        holder.itemUrlText.text = item.URL

        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.URL))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.item, Toast.LENGTH_LONG).show()
            }
        }

        holder.itemView.setOnLongClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            true
        }

    }

    override fun getItemCount(): Int = items.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameText: TextView = itemView.findViewById(R.id.nameTV)
        val itemPriceText: TextView = itemView.findViewById(R.id.priceTV)
        val itemUrlText: TextView = itemView.findViewById(R.id.URLTV)
    }


}