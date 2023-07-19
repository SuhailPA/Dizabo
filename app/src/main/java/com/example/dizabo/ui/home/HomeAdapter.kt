package com.example.dizabo.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.dizabo.data.getalldata.Data
import com.example.dizabo.databinding.HomeItemLayoutBinding

class HomeAdapter() :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var onItemClickListner: ((Data) -> Unit)? = null

    inner class HomeViewHolder(private val binding: HomeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.itemImageView.load(item.imgLink) {
                crossfade(true)
                memoryCachePolicy(CachePolicy.ENABLED)
            }
            binding.itemView.setOnClickListener { onItemClickListner?.let { it(item) } }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            HomeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size ?: 0

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallBack)

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListner = listener
    }

}