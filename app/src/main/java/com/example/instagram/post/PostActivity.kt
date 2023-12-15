package com.example.instagram.post

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.HomeActivity
import com.example.instagram.POST
import com.example.instagram.USER_POST_FOLDER
import com.example.instagram.databinding.ActivityPostBinding
import com.example.instagram.models.Post
import com.example.instagram.utils.uploadImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PostActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    var postList = mutableListOf<String>()

    lateinit var postLink: String

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_POST_FOLDER) {
                binding.imageView7.setImageURI(uri)
            }

            postLink = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.materialToolbar.setNavigationOnClickListener() {
            finish()
        }

        binding.imageView7.setOnClickListener() {
            launcher.launch("image/*")
        }

        binding.cancel.setOnClickListener() {
            finish()
        }

        binding.upload.setOnClickListener() {

            var post: Post = Post(postLink, binding.caption.text?.toString())
            postList.add(postLink)

            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                    .set(post).addOnSuccessListener {
                        startActivity(Intent(this, HomeActivity:: class.java))
                    finish()
                }
            }

        }

    }
}