@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused"
)

package com.example.challangechapter6.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challangechapter6.R
import com.example.challangechapter6.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused"
)
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        binding.Register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        getAcc()
    }
    fun getAcc(){
        sharedPreferences = requireContext().getSharedPreferences("InsertAcc", AppCompatActivity.MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            val getEmail = sharedPreferences.getString("email", "")
            val getPass = sharedPreferences.getString("pass", "")
            val email = binding.insEmail.text.toString()
            val pass = binding.insPass.text.toString()
            if(email == getEmail.toString() && pass == getPass.toString()){
                Toast.makeText(context,"Login Berhasil", Toast.LENGTH_SHORT).show()
                signInWithEmailAndPassword(email = getEmail.toString(), password = getPass.toString())
            }else{
                Toast.makeText(context,"Password Atau Email Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Login berhasil
                    Toast.makeText(context,"Login Berhasil", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(context,"Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }


}


