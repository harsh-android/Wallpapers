package com.hinfo.allgames

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.hinfo.allgames.databinding.ImageItemBinding

class ImagesAdapter(list: ArrayList<ImageModel>) : Adapter<ImagesAdapter.ImagesHolder>() {
var list = list
    lateinit var context: Context
    class ImagesHolder(itemView: ImageItemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        context = parent.context
        var binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImagesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        holder.binding.apply {

            Glide.with(context).load(list.get(position).image).into(imageItem)

        }
    }

}