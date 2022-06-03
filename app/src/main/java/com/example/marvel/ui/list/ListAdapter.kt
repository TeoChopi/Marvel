package com.example.marvel.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marvel.R
import com.example.marvel.databinding.ItemListBinding
import com.example.marvel.repository.model.ResultsItem

class ListAdapter(private val listItemDelegate: ListItemDelegate) : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    private val list = mutableListOf<ResultsItem>()

    class ListHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, list: MutableList<ResultsItem>, listItemDelegate: ListItemDelegate) {
            binding.txtItemDescription.text = list[position].name
            setImage(list[position].thumbnail?.path.plus(".").plus(list[position].thumbnail?.extension))
            binding.item.setOnClickListener {
                listItemDelegate.onItemClick(list[position])
            }
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(R.drawable.ic_faces)
                .error(R.drawable.ic_faces)
                .circleCrop()
                .into(binding.imageViewItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(position, list, listItemDelegate)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResume(data: List<ResultsItem>?) {
        list.clear()
        list.addAll(data!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}