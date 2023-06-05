package com.example.bestkotlinprojectgithub.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bestkotlinprojectgithub.R
import com.example.bestkotlinprojectgithub.model.Item
import com.example.bestkotlinprojectgithub.databinding.ItemKotlinProjectBinding
import com.example.bestkotlinprojectgithub.utils.glideLoad

class KotlinProjectAdapter :
    PagingDataAdapter<Item, KotlinProjectAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemKotlinProjectBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        private val binding: ItemKotlinProjectBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Item) = with(binding) {
            txtOwnerName.text = "$NAME_OF_AUTHOR${item.owner.login}"
            txtRepoName.text = "$NAME_OF_REPOSITORY${item.name}"
            chipStar.text = item.stargazers_count.toString()
            chipFork.text = item.forks_count.toString()

            imgOwner.glideLoad(
                url = item.owner.avatar_url,
                defaultPlaceholder = getPlaceholder(root.context),
                defaultError = getPlaceholder(root.context),
                scaleType = ImageView.ScaleType.FIT_CENTER
            )
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getPlaceholder(context: Context) = context.getDrawable(R.drawable.img_placeholder)


    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem.id == newItem.id
    }

    private companion object{
        const val NAME_OF_AUTHOR = "author:"
        const val NAME_OF_REPOSITORY = "repository:"
    }
}