package com.example.instagram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram.R
import com.example.instagram.adapters.PostRvAdapter
import com.example.instagram.databinding.FragmentPostBinding
import com.example.instagram.models.Post
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
//    private lateinit var postList: ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(inflater, container, false)

//         val adapter = PostRvAdapter(requireContext(), postList)
//
//        binding.rvPost.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        binding.rvPost.adapter = adapter
//
//        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
//            val tempList = arrayListOf<Post>()
//            postList.clear()
//            for (i in it.documents){
//                val post : Post = i.toObject<Post>()!!
//                tempList.add(post)
//            }
//
//            postList.addAll(tempList)
//
//            postList.reverse()
//
//        }
//
//        adapter.notifyDataSetChanged()

        return binding.root
    }

    companion object {
    }
}