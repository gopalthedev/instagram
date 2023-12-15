package com.example.instagram

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT

        val _text =
            "<font color =#FF000000> Not have an account? </font> <font color = #FF0088FF> Register </font>"
        binding.question2.text = Html.fromHtml(_text)

        binding.Lgbt.setOnClickListener() {
            if (binding.username.text?.toString().equals("") or
                binding.logpass.text?.toString().equals("")
            ) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                user = User(
                    binding.username.text?.toString(),
                    binding.logpass.text?.toString()
                )

                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }


            }
        }


    }
}