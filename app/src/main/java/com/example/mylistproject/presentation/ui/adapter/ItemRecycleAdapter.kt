package com.example.mylistproject.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylistproject.databinding.RawItemLayoutBinding
import com.example.mylistproject.domain.model.Item

/**
 * RecyclerView adapter for displaying a list of items.
 */
class ItemRecyclerAdapter :
    RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {

    private var items: List<Item> = emptyList()

    /**
     * Sets the data for the adapter.
     * @param items The list of items to be displayed.
     */
    fun setData(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RawItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size

    /**
     * View holder class for item views.
     * @param binding The binding object containing the layout views.
     */
    inner class ItemViewHolder(private val binding: RawItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds item data to views.
         * @param item The item object to bind.
         * @param position The position of the item in the list.
         */
        fun bind(item: Item, position: Int) {
            binding.nameOfItem.text = item.name
            // Check if it's the first item or the list ID changes
            if (position == 0 || items[position - 1].listId != item.listId) {
                binding.sectionHeader.visibility = View.VISIBLE
                binding.sectionHeader.text = "List ID: ${item.listId}"
            } else {
                binding.sectionHeader.visibility = View.GONE
            }
        }
    }
}
