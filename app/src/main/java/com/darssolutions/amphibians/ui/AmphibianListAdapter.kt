package com.darssolutions.amphibians.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darssolutions.amphibians.databinding.ListViewItemBinding
import com.darssolutions.amphibians.domain.Amphibian

/**
 * Adapter class to populate a RecyclerView with a list of amphibians.
 *
 * @param clickListener Listener for item click events.
 */
class AmphibianListAdapter(private val clickListener: AmphibianListener) :
    ListAdapter<Amphibian, AmphibianListAdapter.AmphibianViewHolder>(DiffCallback) {

    /**
     * ViewHolder class for displaying individual amphibian items in the RecyclerView.
     *
     * @param binding View binding for the item layout.
     */
    class AmphibianViewHolder(
        private var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the amphibian data to the item view and sets up click listener.
         *
         * @param clickListener Listener for item click events.
         * @param amphibian Amphibian data to display.
         */
        fun bind(clickListener: AmphibianListener, amphibian: Amphibian) {
            binding.amphibian = amphibian
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    /**
     * DiffUtil callback for calculating the difference between old and new items.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Amphibian>() {
        override fun areItemsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.type == newItem.type && oldItem.description == newItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmphibianViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AmphibianViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AmphibianViewHolder, position: Int) {
        val amphibian = getItem(position)
        holder.bind(clickListener, amphibian)
    }
}

/**
 * Listener class for item click events in the RecyclerView.
 *
 * @param clickListener Lambda function to handle item click events.
 */
class AmphibianListener(val clickListener: (amphibian: Amphibian) -> Unit) {

    /**
     * Handles the click event for an amphibian item.
     *
     * @param amphibian The clicked amphibian item.
     */
    fun onClick(amphibian: Amphibian) = clickListener(amphibian)
}
