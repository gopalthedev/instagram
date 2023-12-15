package com.example.instagram

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagram.databinding.ActivitySignUpBinding
import com.example.instagram.models.User
import com.example.instagram.utils.uploadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    lateinit var user : com.example.instagram.models.User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let{
            uploadImage(uri, USER_PROFILE_FOLDER){
                if(it == null){

                }else{
                    user.profileImg = it
                    binding.profileImage.setImageURI(uri)
                }
            }

        }
    }


    val binding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT

        user = User()

        binding.button.setOnClickListener(){
            if(binding.Name.text?.toString().equals("") or
            binding.Email.text?.toString().equals("") or
            binding.Password.text?.toString().equals("")){
                Toast.makeText(this@SignUpActivity, "Please fill the details", Toast.LENGTH_SHORT).show()
            }else{
                binding.Email.text?.toString()?.let { it1 ->
                    binding.Password.text?.toString()?.let { it2 ->
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                            it1,
                            it2
                        ).addOnCompleteListener {
                            result->
                            if(result.isSuccessful){
                                user.name = binding.Name.text?.toString()
                                user.email = binding.Email.text?.toString()
                                user.password = binding.Password.text?.toString()

                                Firebase.firestore.collection(USER).document(Firebase.auth.currentUser!!.uid).set(user)
                                    .addOnSuccessListener {
                                        Toast.makeText(this@SignUpActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                                    }

                                finish()
                            }else{
                                Toast.makeText(this, result.exception?.localizedMessage!!, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        binding.plus.setOnClickListener(){
            launcher.launch("image/*")
        }
    }

}