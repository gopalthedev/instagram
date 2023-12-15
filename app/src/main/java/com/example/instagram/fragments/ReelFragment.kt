package com.example.instagram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.R
import com.example.instagram.REELS
import com.example.instagram.adapters.ReelAdapter
import com.example.instagram.databinding.FragmentReelBinding
import com.example.instagram.models.Reels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class ReelFragment : Fragment() {

    private lateinit var binding: FragmentReelBinding

    private lateinit var reelList: ArrayList<Reels>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reelList = ArrayList<Reels>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReelBinding.inflate(inflater, container, false)
        val adapter = ReelAdapter(requireContext(), reelList)
        binding.viewPager.adapter = adapter

        Firebase.firestore.collection(REELS).get().addOnSuccessListener {
            val tempList = arrayListOf<Reels>()
            reelList.clear()
            for (i in it.documents) {
                val reel: Reels = i.toObject<Reels>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            reelList.reverse()
        }

        adapter.notifyDataSetChanged()

        return binding.root
    }

    companion object {
    }
}