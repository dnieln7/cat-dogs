package com.dnieln7.catdogs.ui.cat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.catdogs.databinding.CatListTileBinding
import com.dnieln7.catdogs.databinding.TagBinding
import com.dnieln7.catdogs.domain.cat.Cat

class CatListAdapter(private val cats: List<Cat>) :
    RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {

    class CatViewHolder(private val binding: CatListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            with(binding) {
                name = cat.name
                imageUrl = if (cat.image != null) cat.image.url ?: "" else ""
                tags = cat.temperament.split(",")
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

        class TagViewHolder(private val binding: TagBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(tag: String) {
                binding.tag = "• $tag"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
            return TagViewHolder(
                TagBinding.inflate(
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