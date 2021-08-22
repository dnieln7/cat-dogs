package com.dnieln7.catdogs.ui.cat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dnieln7.catdogs.R
import com.dnieln7.catdogs.databinding.CatListTileBinding
import com.dnieln7.catdogs.databinding.TagTextBinding
import com.dnieln7.catdogs.domain.cat.Cat

class CatListAdapter(private val cats: List<Cat>) :
    RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {

    class CatViewHolder(private val binding: CatListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            val uri = cat.image?.url?.toUri()?.buildUpon()?.scheme("https")?.build()

            with(binding) {
                name.text = cat.name
                image.load(uri) {
                    crossfade(true)
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
                tags.setHasFixedSize(true)
                tags.adapter = TagAdapter(cat.temperament.split(","))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            CatListTileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats[position])
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    class TagAdapter(
        private val tags: List<String>
    ) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

        class TagViewHolder(private val binding: TagTextBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(tag: String) {
                binding.tag.text = "• $tag"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
            return TagViewHolder(
                TagTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
            holder.bind(tags[position])
        }

        override fun getItemCount(): Int {
            return tags.size
        }
    }
}