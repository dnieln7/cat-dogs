package com.dnieln7.catdogs.ui.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.dnieln7.catdogs.R
import com.dnieln7.catdogs.domain.cat.Cat
import com.dnieln7.catdogs.ui.cat.CatListAdapter
import com.dnieln7.catdogs.utils.ApiState
import com.google.android.material.progressindicator.BaseProgressIndicator


@BindingAdapter("app:catData")
fun setListData(recyclerView: RecyclerView, data: List<Cat>) {
    recyclerView.adapter = CatListAdapter(data)
}

@BindingAdapter("app:isRefreshing")
fun setIsRefreshing(swipeRefreshLayout: SwipeRefreshLayout, apiState: ApiState) {
    swipeRefreshLayout.isRefreshing = apiState == ApiState.Loading
}

@BindingAdapter("app:onSwipe")
fun setOnSwipe(swipeRefreshLayout: SwipeRefreshLayout, action: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener { action() }
}

@BindingAdapter("app:progressVisibility")
fun setProgressVisible(progress: BaseProgressIndicator<*>, apiState: ApiState) {
    progress.visibility = if (apiState == ApiState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("app:errorVisibility")
fun setErrorVisible(view: View, apiState: ApiState) {
    view.visibility = if (apiState is ApiState.Error) View.VISIBLE else View.GONE
}

@BindingAdapter("app:error")
fun setError(textView: TextView, apiState: ApiState) {
    if (apiState is ApiState.Error) {
        textView.text = apiState.error
    }
}

@BindingAdapter("app:ErrorIcon")
fun setErrorIcon(imageView: ImageView, apiState: ApiState) {
    if (apiState is ApiState.Error) {
        imageView.load(R.drawable.ic_no_network)
    }
}

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    val uri = url?.toUri()?.buildUpon()?.scheme("https")?.build()

    imageView.load(uri) {
        crossfade(true)
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}

@BindingAdapter("app:tagData")
fun setTagData(recyclerView: RecyclerView, data: List<String>) {
    recyclerView.adapter = CatListAdapter.TagAdapter(data)
}