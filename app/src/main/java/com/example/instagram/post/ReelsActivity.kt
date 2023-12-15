package com.example.instagram.post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instagram.HomeActivity
import com.example.instagram.POST
import com.example.instagram.R
import com.example.instagram.REELS
import com.example.instagram.USER_POST_FOLDER
import com.example.instagram.USER_REELS_FOLDER
import com.example.instagram.databinding.ActivityReelsBinding
import com.example.instagram.models.Post
import com.example.instagram.models.Reels
import com.example.instagram.utils.uploadImage
import com.example.instagram.utils.uploadVideo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReelsActivity : AppCompatActivity() {

    private var reelsList = mutableListOf<String>()

    private lateinit var reelLink : String

    private lateinit var progressDialog: ProgressDialog

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri?.let{
            uploadVideo(uri,this@ReelsActivity, progressDialog ,USER_REELS_FOLDER){
                Glide.with(this).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.videoView3)
            }

            reelLink = uri.toString()
        }
    }

    private val binding by lazy{
        ActivityReelsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        setSupportActionBar(binding.materialToolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.materialToolbar.setNavigationOnClickListener(){
            finish()
        }

         binding.cancel.setOnClickListener(){
             finish()
         }

        binding.upload.setOnClickListener(){
            reelsList.add(reelLink)

            var reel: Reels = Reels(reelLink, binding.caption.text?.toString())

            Firebase.firestore.collection(REELS).document().set(reel).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REELS).document()
                    .set(reel).addOnSuccessListener {
                        startActivity(Intent(this, HomeActivity:: class.java))
                        finish()
                    }
            }
        }

        binding.videoView3.setOnClickListener(){
            launcher.launch("video/*")
        }
    }
}