package com.example.instagram.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.databinding.HomePostModelBinding
import com.example.instagram.models.Post
import com.squareup.picasso.Picasso

class HomePageAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<HomePageAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: HomePostModelBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomePostModelBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(postList[position].postUrl).into(holder.binding.postImage)
        holder.binding.caption.text = postList[position].postCaption
    }


}