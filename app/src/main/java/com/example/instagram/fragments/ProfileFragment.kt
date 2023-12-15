package com.example.instagram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.USER
import com.example.instagram.adapters.ViewPagerAdapter
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.models.User
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding  = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, position ->

            when(position){
                0 -> tab.text = "Posts"
                1 -> tab.text = "Reels"
            }
        }.attach()

        return binding.root
    }

    companion object {
    }

    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER)
            .document(Firebase.auth.currentUser!!.uid)
            .get().addOnSuccessListener {
                val user: User = it.toObject<User>()!!

                binding.usrname.text = user.name
                binding.bioText.text = user.email

                if (!user.profileImg.isNullOrBlank()) {
                    Picasso.get().load(user.profileImg).into(binding.profilePhoto)
                }


            }


    }
}