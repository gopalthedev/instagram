package com.example.instagram.fragments

//import com.example.instagram.databinding.FragmentProfileBinding
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.databinding.FragmentAddBinding
import com.example.instagram.post.PostActivity
import com.example.instagram.post.ReelsActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class addFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener(){
            activity?.startActivity(Intent(requireContext(), PostActivity:: class.java))
        }

        binding.reel.setOnClickListener(){
            activity?.startActivity(Intent(requireContext(), ReelsActivity:: class.java))
        }

        return binding.root
    }

    companion object {
    }
}