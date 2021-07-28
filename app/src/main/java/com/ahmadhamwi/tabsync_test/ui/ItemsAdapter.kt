package com.ahmadhamwi.tabsync_test.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync_test.R
import com.ahmadhamwi.tabsync_test.model.Item

class ItemsAdapter(
    private val context: Context,
    private var items: List<Item>
) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {
            view.findViewById<TextView>(R.id.textViewContent).text = item.content
        }
    }
}


