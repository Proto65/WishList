package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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