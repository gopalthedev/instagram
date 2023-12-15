package com.example.instagram.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.POST
import com.example.instagram.adapters.HomePageAdapter
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.models.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

   private lateinit var binding: FragmentHomeBinding
  private lateinit var postList: ArrayList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postList = ArrayList<Post>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = HomePageAdapter(requireContext(), postList)
        binding.homePager.layoutManager = LinearLayoutManager(requireContext())
        binding.homePager.adapter = adapter

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            val tempList = arrayListOf<Post>()
            postList.clear()
            for (i in it.documents) {
                val post: Post = i.toObject<Post>()!!
                tempList.add(post)
            }

            tempList.reverse()
            postList.addAll(tempList)

        }

        adapter.notifyDataSetChanged()
        return binding.root
    }

    companion object {

    }
}