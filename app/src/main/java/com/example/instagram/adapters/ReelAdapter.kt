package com.example.instagram.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.databinding.ReelDgBinding
import com.example.instagram.models.Reels
import com.squareup.picasso.Picasso

class ReelAdapter (var context: Context, var reelList : ArrayList<Reels>): RecyclerView.Adapter<ReelAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ReelDgBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelAdapter.ViewHolder {
        var binding = ReelDgBinding.inflate(LayoutInflater.from(context), parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReelAdapter.ViewHolder, position: Int) {
        Picasso.get().load(reelList.get(position).profileImg).into(holder.binding.profilePhoto)
        holder.binding.textView.setText(reelList.get(position).reelCaption)
        holder.binding.videoView.setVideoPath(reelList.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener(){
            holder.binding.videoView.start()
        }
    }

    override fun getItemCount(): Int {
       return reelList.size
    }
}