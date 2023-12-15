package com.example.instagram.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.databinding.ViewRvPostBinding
import com.example.instagram.models.Post
import com.squareup.picasso.Picasso

class PostRvAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostRvAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ViewRvPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewRvPostBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(postList[position].postUrl).into(holder.binding.PostImage)
    }
}