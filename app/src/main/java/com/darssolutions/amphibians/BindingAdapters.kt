package com.darssolutions.amphibians

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darssolutions.amphibians.domain.Amphibian
import com.darssolutions.amphibians.domain.AmphibianApiStatus
import com.darssolutions.amphibians.domain.AmphibianApiStatus.*
import com.darssolutions.amphibians.ui.AmphibianListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Amphibian>?) {
    val adapter = recyclerView.adapter as AmphibianListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: AmphibianApiStatus?) {
    when (status) {
        LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DONE -> {
            statusImageView.visibility = View.GONE
        }
        null -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
