package com.example.instagram.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instagram.databinding.ViewRvPostBinding
import com.example.instagram.databinding.ViewRvReelsBinding
import com.example.instagram.models.Reels
import com.squareup.picasso.Picasso

class ReelsRvAdapter(var context: Context, var reelsList: ArrayList<Reels>) :
    RecyclerView.Adapter<ReelsRvAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ViewRvReelsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ViewRvReelsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelsList.size
    }

    override fun onBindViewHolder(holder: ReelsRvAdapter.ViewHolder, position: Int) {


        Glide.with(context).load(reelsList.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.reelView)
    }
}